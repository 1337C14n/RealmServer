package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import main.logging.Logger;
import chat.Channel;
import chat.ChannelHandler;
import chat.mute.Muted;
import chat.privateMessage.PrivateMessage;

import com.google.common.base.Throwables;
import configuration.Config;

public enum DataBaseConnector {
  INSTANCE;
  
  private Statement st = null;
  private ResultSet rs = null;
  private Connection con;
  
  private String dataBase;
  private String url;
  private String user; 
  private String password;
  private String connectionString;
 
  private boolean connected = false;
  
  private DataBaseConnector() {
    
    this.dataBase = Config.INSTANCE.getConfig().getDatabase();
    this.url = Config.INSTANCE.getConfig().getUrl();
    this.user = Config.INSTANCE.getConfig().getUser();
    this.password = Config.INSTANCE.getConfig().getPassword();
    
    this.connectionString = "jdbc:mysql://" + url + ":3306/" + dataBase;
    
    connect();
  }

  private void connect() {

    try {
      con = DriverManager.getConnection(connectionString, user, password);
      setConnected(true);
    } catch (SQLException ex) {
      Logger.critical(Throwables.getStackTraceAsString(ex));
      setConnected(false);
    }
    initialize();
  }

  public void sendQuery(String query) {
    try {
      st = getConnection().createStatement();
      st.executeUpdate(query);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public boolean channelExists(String name) {
    String query = "SELECT * FROM `channels` WHERE name = ?;";
    try {
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, name);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        return true;
      }
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
    return false;
  }
  
  public boolean privateMessageOpen(boolean isOpen) {
	  String query = "SELECT * FROM 'privatemessages' WHERE isOpen = ?;";
	  try {
		  PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		  preparedStatement.setBoolean(1, isOpen);
		  rs = preparedStatement.executeQuery();
		  while(rs.next()) {
			  return true;
		  }
	  } catch(SQLException e) {
		  Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
	  }
	  return false;
  }

  public void muteLog(String name, String mutee, String reason) {
    if (playerExists(name)) {
      try {
        PreparedStatement statement;
        statement = getConnection().prepareStatement("INSERT INTO mutes " + "(name, reason, mutee) " + "VALUES (?, ?, ?);");

        statement.setString(1, name);
        statement.setString(2, reason);
        statement.setString(3, mutee);

        statement.execute();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  public boolean isMuted(String name) {
    String query = "SELECT muted, muteUntil FROM `players` WHERE playername = ?;";

    Boolean muted = false;

    try {
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, name);
      rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Boolean banned = rs.getBoolean("muted");
        Timestamp bannedTimestamp = rs.getTimestamp("muteUntil");

        Calendar bannedCal = null;
        if (banned && bannedTimestamp != null) {
          bannedCal = Calendar.getInstance();
          bannedCal.setTimeInMillis(bannedTimestamp.getTime());

          Calendar currentTime = Calendar.getInstance();

          if (currentTime.compareTo(bannedCal) < 0) {
            muted = true;
          } else {
            return false;
          }
        } else if (banned && bannedTimestamp == null) {
          muted = true;
        }

        if (muted) {
          return true;
        }

      }
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }

    return false;
  }

  public void muteOffline(String name, Calendar cal, boolean muted) {
    Date date;

    try {
      date = new Date(cal.getTimeInMillis());
    } catch (NullPointerException e) {
      date = null;
    }

    try {
      PreparedStatement statement;
      statement = getConnection().prepareStatement("UPDATE players " + "SET muted = ?, muteUntil = ? " + "WHERE playername = ?;");

      statement.setBoolean(1, muted);
      statement.setTimestamp(2, (date == null) ? null : new Timestamp(date.getTime()));
      statement.setString(3, name);

      statement.executeUpdate();
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
  }

  public void banned(String name, String banee, boolean isBanned, Calendar cal, String reason) {
    Date date;

    try {
      date = new Date(cal.getTimeInMillis());
    } catch (NullPointerException e) {
      date = null;
    }

    if (playerExists(name)) {
      try {
        PreparedStatement statement;
        String action = isBanned ? "Unban" : "Ban";
        statement = getConnection().prepareStatement("INSERT INTO bans " + "(name, action, reason, banee) " + "VALUES (?, ?, ?, ?);");

        statement.setString(1, name);
        statement.setString(2, action);
        statement.setString(3, reason);
        statement.setString(4, banee);

        statement.execute();
      } catch (SQLException e1) {
        Logger.critical(Throwables.getStackTraceAsString(e1));
      }

      try {
        PreparedStatement statement;
        statement = getConnection().prepareStatement("UPDATE players " + "SET banned = ?, bannedUntil = ? " + "WHERE playername = ?;");

        statement.setBoolean(1, isBanned);
        statement.setTimestamp(2, (date == null) ? null : new Timestamp(date.getTime()));
        statement.setString(3, name);

        statement.executeUpdate();
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
  }

  public void warn(String name, String warnedPlayer, String reason) {
    try {
      PreparedStatement statement;
      statement = getConnection().prepareStatement("INSERT INTO warns " + "(warnee, name, reason) " + "VALUES (?, ?, ?);");

      statement.setString(1, name);
      statement.setString(2, warnedPlayer);
      statement.setString(3, reason);

      statement.execute();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }    
  }

  public boolean isBanned(String name) {
    String query = "SELECT banned, bannedUntil FROM `players` WHERE playername = ?;";

    Boolean kick = false;

    try {
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, name);
      rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Boolean banned = rs.getBoolean("banned");
        Timestamp bannedTimestamp = rs.getTimestamp("bannedUntil");

        Calendar bannedCal = null;
        if (banned && bannedTimestamp != null) {
          bannedCal = Calendar.getInstance();
          bannedCal.setTimeInMillis(bannedTimestamp.getTime());

          Calendar currentTime = Calendar.getInstance();

          if (currentTime.compareTo(bannedCal) < 0) {
            kick = true;
          } else {
            banned(name, "Server", false, null, "Auto Unban");
            return false;
          }
        } else if (banned && bannedTimestamp == null) {
          kick = true;
        }

        if (kick) {
          return true;
        }

      }
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }

    return false;
  }

  public boolean playerExists(String name) {
    String query = "SELECT * FROM `players` WHERE playername = ?;";
    try {
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, name);
      rs = preparedStatement.executeQuery();

      while (rs.next()) {
        return true;
      }
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
    return false;
  }

  private void initialize() {
    Logger.log("Checking Tables", Logger.INFO);
    String query = "CREATE TABLE IF NOT EXISTS `channels` ("
        + "`name` varchar(32) NOT NULL,"
        + "`prefixenabled` BOOLEAN NOT NULL,"
        + "`owners` text NOT NULL,"
        + "`unfilteredname` varchar(5) DEFAULT NULL,"
        + "`prefix` varchar(5) DEFAULT NULL,"
        + "`password` varchar(32) DEFAULT NULL,"
        + "PRIMARY KEY (name))"
        + "ENGINE=InnoDB DEFAULT CHARSET=latin1;";
    sendQuery(query);
    query = "CREATE TABLE IF NOT EXISTS `players` ("
        + "`playername` varchar(32) NOT NULL,"
        + "`activechannel` varchar(32) NOT NULL,"
        + "`channels` text NOT NULL,"
        + "`muted` tinyint(1) DEFAULT NULL,"
        + "`muteUntil` timestamp NULL DEFAULT NULL,"
        + "`banned` tinyint(1) DEFAULT NULL,"
        + "`bannedUntil` timestamp NULL DEFAULT NULL,"
        + "`server` varchar(64) NULL DEFAULT 'hub',"
        + "PRIMARY KEY (`playername`))"
        + " ENGINE=InnoDB DEFAULT CHARSET=latin1;";
    sendQuery(query);
    query = "CREATE TABLE IF NOT EXISTS `bans` ("
        + "`id` int(32) NOT NULL AUTO_INCREMENT,"
        + "`name` varchar(32) DEFAULT NULL,"
        + "`reason` text,"
        + "`time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,"
        + "`banee` varchar(32) DEFAULT NULL,"
        + "PRIMARY KEY (`id`))"
        + "ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;";
    sendQuery(query);
    query = "CREATE TABLE IF NOT EXISTS `mutes` ("
        + "`id` int(32) NOT NULL AUTO_INCREMENT,"
        + "`name` varchar(32) DEFAULT NULL,"
        + "`reason` text,"
        + "`time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,"
        + "`mutee` varchar(32) DEFAULT NULL,"
        + "PRIMARY KEY (`id`))"
        + "ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;";
    sendQuery(query);
    query = "CREATE TABLE IF NOT EXISTS `warns` ("
        + "`id` int(32) NOT NULL AUTO_INCREMENT,"
        + "`name` varchar(32) DEFAULT NULL,"
        + "`reason` text,"
        + "`time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,"
        + "`warnee` varchar(32) DEFAULT NULL,"
        + "PRIMARY KEY (`id`))"
        + "ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;";
    sendQuery(query);
  }

  public boolean populatePlayer(String name) {
    Logger.log("Loading players", Logger.INFO);
    String query = "SELECT * FROM `players` WHERE playername = ?;";
    try {

      // Boolean kick = false;

      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, name);
      rs = preparedStatement.executeQuery();

      while (rs.next()) {
        String activeChannel = rs.getString("activechannel");
        Boolean muted = rs.getBoolean("muted");
        Timestamp timestamp = rs.getTimestamp("muteUntil");

        CopyOnWriteArrayList<String> channels = new CopyOnWriteArrayList<String>();
        for (String channel : rs.getString("channels").split(",")) {
          channels.add(channel);
        }
        
        HashMap<String, Boolean> ignoredPlayersTemp = new HashMap<String, Boolean>();
        String allIgnoredPlayers = rs.getString("ignored");
        
        if(allIgnoredPlayers != null){
          for(String ignoredPlayer : rs.getString("ignored").split(",")){
            ignoredPlayersTemp.put(ignoredPlayer, true);
          }
        }


        Player player = new Player(name, activeChannel, channels);
        player.setMuted(muted);
        player.setIgnoredPlayers(ignoredPlayersTemp);

        PlayerHandler.INSTANCE.addPlayer(player);

        Calendar cal = null;
        if (timestamp != null) {
          cal = Calendar.getInstance();
          cal.setTimeInMillis(timestamp.getTime());
          player.setUnmuteDate(cal);
          Muted.INSTANCE.addPlayer(name);
        }

        return true;
      }
    } catch (SQLException ex) {
      Logger.log(ex.getMessage(), Logger.CRITICAL);
    }
    return false;
  }

  public void populateChannels() {
    Logger.log("Loading channels", Logger.INFO);
    String Query = "SELECT * FROM `channels`";
    try {
      st = getConnection().createStatement();
      rs = st.executeQuery(Query);
      while (rs.next()) {
        String channelName = rs.getString("name");

        CopyOnWriteArrayList<String> owners = new CopyOnWriteArrayList<String>();
        for (String player : rs.getString("owners").split(",")) {
          owners.add(player);
        }
        
        int id = rs.getInt("id");
        String actualName = rs.getString("unfilteredname");
        String prefix = rs.getString("prefix");
        String chPassword = rs.getString("password");
        boolean prefixEnabled = rs.getBoolean("prefixenabled");
        Channel channel = new Channel(id, channelName, actualName, prefix, chPassword, owners, prefixEnabled);
        ChannelHandler.INSTANCE.addChannel(channel);
        Logger.log("Loading Channel: " + channel.getChannelName(), Logger.INFO);
        
      }
    } catch (SQLException ex) {
      Logger.log(ex.getMessage(), Logger.CRITICAL);
    }
  }

  public void populate() {
    populateChannels();
  }
  
  public int getChannelId(Channel channel){
    saveChannel(channel);
    try {
      String query = "SELECT id FROM `channels` WHERE name = ?";
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, channel.getChannelName());
      rs = preparedStatement.executeQuery();
      
      while (rs.next()) {
        int id = rs.getInt("id");
        return id;
      }
    } catch (SQLException ex) {
      Logger.log(ex.getMessage(), Logger.CRITICAL);
    }
    return -1;
  }
  
  public int getPrivateMessageId(PrivateMessage privateMessage) {
		savePrivateMessage(privateMessage);
		try {
			String query = "SELECT id FROM 'privateMessages' WHERE player1 = ? AND player2 = ?";
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setString(1, privateMessage.getPlayer1());
			preparedStatement.setString(2, privateMessage.getPlayer2());
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				return id;
			}
		} catch(SQLException ex) {
			Logger.log(ex.getMessage(), Logger.CRITICAL);
		}
		return 0;
	}

  public void saveChannel(Channel channel) {
    String owners = channel.getOwners().toString();
    owners = owners.substring(1, owners.length() - 1);
    if (channelExists(channel.getChannelName())) {
      PreparedStatement statement;
      try {
        statement = getConnection().prepareStatement(
            "UPDATE channels " + "SET name = ?, owners = ?, prefixenabled = ?, unfilteredName = ?, prefix = ?, password = ? " + "WHERE id = ?;");

        statement.setString(1, channel.getChannelName());
        statement.setString(2, owners);
        statement.setBoolean(3, channel.isChannelPrefixEnabled());
        statement.setString(4, channel.getName());
        statement.setString(5, channel.getPrefix());
        statement.setString(6, channel.getPassword());
        statement.setInt(7, channel.getId());
        
        statement.executeUpdate();
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    } else {
      PreparedStatement statement;
      try {
        statement = getConnection().prepareStatement(
            "INSERT INTO channels " + "(name, owners, prefixenabled, unfilteredName, prefix, password) " + "VALUES (?, ?, ?, ?, ?, ?);");

        statement.setString(1, channel.getChannelName());
        statement.setString(2, owners);
        statement.setBoolean(3, channel.isChannelPrefixEnabled());
        statement.setString(4, channel.getName());
        statement.setString(5, channel.getPrefix());
        statement.setString(6, channel.getPassword());
        

        statement.execute();
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
  }
  
  public void savePrivateMessage(PrivateMessage privateMessage) {
	  if(privateMessageOpen(privateMessage.isOpen())) {
		  PreparedStatement statement;
	      try {
	          statement = getConnection().prepareStatement(
	              "UPDATE privatemessages " + "SET player1 = ?, player2 = ?, isOpen = ?" + "WHERE id = ?;");

	          statement.setString(1, privateMessage.getPlayer1());
	          statement.setString(2, privateMessage.getPlayer2());
	          statement.setBoolean(3, privateMessage.isOpen());
	          statement.setInt(4, privateMessage.getId());
	          
	          statement.executeUpdate();
	        } catch (SQLException e) {
	          Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
	        }
	  } else {
	      PreparedStatement statement;
	      try {
	        statement = getConnection().prepareStatement(
	            "INSERT INTO privateMessages " + "(player1, player2, isOpen) " + "VALUES (?, ?, ?);");

	        statement.setString(1, privateMessage.getPlayer1());
	        statement.setString(2, privateMessage.getPlayer2());
	        statement.setBoolean(3, privateMessage.isOpen());
	        

	        statement.execute();
	      } catch (SQLException e) {
	        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
	      }
	    }
  }
  
  public String[] getPlayerPermissions(String playerName, String server){
    /*
     * We will start getting players permissions by querying the groups the player is in
     */
    
    ArrayList<String> permissionNodes = new ArrayList<String>();
    
    ArrayList<Integer> playerGroups = new ArrayList<Integer>();
    
    System.out.println("Player Name: '" + playerName + "'  Server: '" + server + "'");
    
    String query = 
        "SELECT groupid "
        + "FROM playergroups "
        + "INNER JOIN players "
        + " ON playerid = players.id "
        + "WHERE playername = ?";
    
    try {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        
        preparedStatement.setString(1, playerName);
        
        rs = preparedStatement.executeQuery();
        
        System.out.println("Getting permissions");
        
        while (rs.next()) {
          int groupid = rs.getInt("groupid");
          playerGroups.add(groupid);
        }
        
        //All players are in group 2
        playerGroups.add(2);
        
        /*
         * Now to get permission nodes from each group
         */
        
        
        //Base Query
        query = "SELECT node "
            + "FROM permissionnodes "
            + "INNER JOIN servers "
            + "  ON permissionnodes.serverid = servers.id "
            + "INNER JOIN players "
            + "  ON permissionnodes.playerid = players.id ";
        
        for(int i = 0; i < playerGroups.size(); i++){
          int group = playerGroups.get(i);
          
          System.out.println("Group ID: " + group);
        
          if(i == 0) {
            query += "WHERE ((playername = ? OR players.id = 7) "
            + " AND (servers.name = ? OR servers.id = 6) "
            + " AND (permissionnodes.groupid = " + group + ")) ";
          } else {
            query += "OR ((playername = ? OR players.id = 7) "
            + " AND (servers.name = ? OR servers.id = 6) "
            + " AND (permissionnodes.groupid = " + group + ")) ";
          }
             
        }
        query += ";";
        
        preparedStatement = getConnection().prepareStatement(query);
        
        for(int i = 1; i < playerGroups.size() * 2; i += 2){
          preparedStatement.setString(i, playerName);
          preparedStatement.setString(i + 1, server);
        }
        
        
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
          String node = rs.getString("node");
          System.out.println("Node: " + node);
          permissionNodes.add(node);
        }
          
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return permissionNodes.toArray(new String[permissionNodes.size()]);
  }
  
  public String getPlayerPrefix(String playerName){
    String prefix = "";
    
    String query = 
        "SELECT prefixes.prefix "
        + "FROM prefixes "
        + "INNER JOIN playergroups "
        + "  ON prefixes.groupid = playergroups.groupid "
        + "INNER JOIN players "
        + "  ON playergroups.playerid = players.id "
        + "WHERE players.playername = ? AND "
        + "donatorlevel IN (SELECT groupid FROM playergroups "
        + "INNER JOIN players "
        + "ON playergroups.playerid = players.id "
        + "WHERE players.playername = ?) "
        + "ORDER BY priority DESC LIMIT 1;";
    
    try {
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      
      preparedStatement.setString(1, playerName);
      preparedStatement.setString(2, playerName);
      
      rs = preparedStatement.executeQuery();

      while (rs.next()) {
        prefix = rs.getString("prefix");
      }
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
    
    return prefix;
  }

  public void savePlayer(Player player) {
    Date date;

    try {
      date = new Date(player.getUnmuteDate().getTimeInMillis());
    } catch (NullPointerException e) {
      date = null;
    }

    //prepare channels to be saved
    String channels = player.getChannels().toString().replace(" ", "");
    channels = channels.substring(1, channels.length() - 1);
    
    String ignoredPlayers = player.getIgnoredPlayers().toString().replace(" ", "");
    ignoredPlayers = ignoredPlayers.substring(1, ignoredPlayers.length() - 1);
    
    if (playerExists(player.getName())) {
      PreparedStatement statement;
      try {
        statement = getConnection().prepareStatement(
            "UPDATE players " + "SET playername = ?, activechannel = ?, channels = ?, muted = ?, muteUntil = ?, ignored = ?, lastPMFrom = ? " + "WHERE playername = ?;");

        statement.setString(1, player.getName());
        statement.setString(2, player.getActiveChannel());
        statement.setString(3, channels);
        statement.setBoolean(4, player.isMuted());
        statement.setTimestamp(5, (date == null) ? null : new Timestamp(date.getTime()));
        statement.setString(6, ignoredPlayers);
        statement.setString(7, player.getLastPMFrom());
        statement.setString(8, player.getName());

        statement.executeUpdate();
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    } else {
      try {
        PreparedStatement statement = getConnection().prepareStatement(
            "INSERT INTO players " + "(playername, activechannel, channels, muted, muteUntil, ignored, lastPMFrom) " + "VALUES (?, ?, ?, ?, ?, ?, ?);");

        statement.setString(1, player.getName());
        statement.setString(2, player.getActiveChannel());
        statement.setString(3, channels);
        statement.setBoolean(4, player.isMuted());
        statement.setTimestamp(5, (date == null) ? null : new Timestamp(date.getTime()));
        statement.setString(6, ignoredPlayers);
        statement.setString(7, player.getLastPMFrom());

        statement.execute();
        
        statement = getConnection().prepareStatement(
            "INSERT INTO playergroups (playerid, groupid) SELECT id, 4 FROM players WHERE playername = ?");

        statement.setString(1, player.getName());

        statement.execute();
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
  }
  
  public boolean promotePlayer(String name){
    
    if(!this.playerExists(name)){
      return false;
    }
    
    try {
      PreparedStatement statement = getConnection().prepareStatement(
          "CALL PromotePlayer(?)");

      statement.setString(1, name);

      statement.execute();
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      return false;
    }
    return true;
  }
  public boolean demotePlayer(String name){
    
    if(!this.playerExists(name)){
      return false;
    }
    
    try {
      PreparedStatement statement = getConnection().prepareStatement(
          "CALL DemotePlayer(?)");

      statement.setString(1, name);

      statement.execute();
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      return false;
    }
    return true;
  }
  public void removeChannel(String channelName) {
    if (channelExists(channelName)) {
      PreparedStatement statement;
      String query = "DELETE FROM `channels` WHERE name = ?;";
      try {
        statement = getConnection().prepareStatement(query);
        statement.setString(1, channelName);

        statement.execute();
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
  }
  
  /**
   * Gets the given players server
   * 
   * @param name Players name
   * @return returns the server name if exists else returns null
   */
  public String getPlayerServer(String name) {
    if(!this.playerExists(name)){
      return "hub";
    }
    
    String query = "SELECT `name` FROM `servers` INNER JOIN players ON servers.id = players.serverid WHERE players.playername = ?;";
    try {
      PreparedStatement preparedStatement = getConnection().prepareStatement(query);
      preparedStatement.setString(1, name);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        String server = rs.getString("name");
        Logger.log("Player is on server " + server, Logger.INFO);
        return server;
      }
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
    return null;
  }

  /**
   * Set players server within the database
   * 
   * @param playerName
   * @param serverName
   */
  public void setPlayerServer(String playerName, String serverName){
    try {
      PreparedStatement statement = getConnection().prepareStatement(
          "UPDATE players SET serverid = (SELECT id FROM servers WHERE NAME = ?) WHERE playername = ?;");

      statement.setString(1, serverName);
      statement.setString(2, playerName);
      
      statement.executeUpdate();
    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
  }
  
  /**
   * Registers a server with database if the server does not exist'
   * 
   * @param serverName
   */
  public void registerServer(String serverName){
    try {
      boolean serverExists = false;
      PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT id FROM servers WHERE NAME = ?;");
      preparedStatement.setString(1, serverName);
      rs = preparedStatement.executeQuery();
      
      while (rs.next()) {
        serverExists = true;
      }
      
      if(!serverExists){
        PreparedStatement statement = getConnection().prepareStatement(
            "INSERT INTO servers(NAME) VALUES (?);");

        statement.setString(1, serverName);
        
        statement.executeUpdate();
      }
      

    } catch (SQLException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
  }
  public Connection getConnection() throws SQLException {
    
    //return this.pool.getConnection();
    
    if (con == null) {
      connect();
    } else {
      try {
        if (!con.isValid(1)) {
          Logger.log("Connection stale. Reconnecting", Logger.INFO);
          con.close();
        }

        if (con.isClosed()) {
          con = null;
          connect();
        }
      } catch (SQLException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }

    return con;
  }

  public boolean isConnected() {
    return connected;
  }

  public void setConnected(boolean connected) {
    this.connected = connected;
  }
}