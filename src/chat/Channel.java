package chat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import main.DataBaseConnector;
import main.Player;
import main.Client;
import main.ClientWriter;
import main.Clients;
import main.PlayerHandler;
import packets.ChannelMessage;

public class Channel {

  private int id;
  private String channelName;
  private String name;
  private String prefix;
  private String password;
  private boolean channelPrefixEnabled;
  private boolean isPrivate;
  private CopyOnWriteArrayList<String> owners;
  private CopyOnWriteArrayList<String> players;

  ConcurrentLinkedQueue<ChatMessage> channelLog = new ConcurrentLinkedQueue<ChatMessage>();

  /*
   * Full constructor for populating channels
   */
  public Channel(int id, String channelName, String name, String prefix, String password, CopyOnWriteArrayList<String> owners, boolean channelPrefixEnabled) {

    this.id = id;
    this.channelName = channelName;
    this.prefix = prefix;
    this.password = password;
    this.name = name;
    this.owners = owners;
    this.channelPrefixEnabled = channelPrefixEnabled;
    this.players = new CopyOnWriteArrayList<String>();

    Thread clientThread = new Thread(new ChatLog(this));
    clientThread.start();
  }

  public Channel(String channelName, String name, String prefix, CopyOnWriteArrayList<String> owners, boolean channelPrefixEnabled) {

    this.channelName = channelName;
    this.prefix = prefix;
    this.password = null;
    this.name = name;
    this.owners = owners;
    this.channelPrefixEnabled = channelPrefixEnabled;
    this.players = new CopyOnWriteArrayList<String>();

    /*
     * And id is required get it from the database; This also registers the
     * channel with the database;
     */
    this.id = DataBaseConnector.INSTANCE.getChannelId(this);

    Thread clientThread = new Thread(new ChatLog(this));
    clientThread.start();
  }

  public Channel(String channelName, String name, String prefix, String password, CopyOnWriteArrayList<String> owners, boolean channelPrefixEnabled) {
    this.channelName = channelName;
    this.prefix = prefix;
    this.password = password;
    this.name = name;
    this.owners = owners;
    this.channelPrefixEnabled = channelPrefixEnabled;
    this.players = new CopyOnWriteArrayList<String>();

    /*
     * And id is required get it from the database; This also registers the
     * channel with the database;
     */
    this.id = DataBaseConnector.INSTANCE.getChannelId(this);

    Thread clientThread = new Thread(new ChatLog(this));
    clientThread.start();
  }

  public String getPlayerThatIsNot(String player) {
    for (String channelPlayer : players) {
      if (!channelPlayer.equals(player)) {
        return channelPlayer;
      }
    }
    return null;
  }

  public void removePlayer(String player) {
    players.remove(player);
  }

  public void addPlayer(String player) {
    players.add(player);
  }

  public boolean ContainsPlayer(String player) {
    return players.contains(player);
  }

  public void SendMessage(String message, String senderPrefix, String sender, boolean senderIsModerator) {

    Calendar cal = Calendar.getInstance();
    
    ChatMessage chatMessage = new ChatMessage(sender, message, cal.getTime());

    channelLog.add(chatMessage);

    // If monitor client is connected also send the message to that client.

    for (int chatClient : Clients.INSTANCE.getChatClients()) {
      Clients.INSTANCE.getClientFromInt(chatClient).getWriter().addToQueue(new ChannelMessage(channelName, senderPrefix, sender, null, message, channelPrefixEnabled));
    }

    HashMap<Integer, ArrayList<String>> playerMap = new HashMap<Integer, ArrayList<String>>();

    for (String player : players) {

      Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);

      if (chatPlayer == null) {
        removePlayer(player);
        continue;
      } else if (chatPlayer.isIgnoring(sender) && !senderIsModerator) {
        // This player is ignoring the message sender
        continue;
      }

      int activeServer = chatPlayer.getActiveServer();

      ArrayList<String> arrayList = playerMap.containsKey(activeServer) ? playerMap.get(activeServer) : new ArrayList<String>();

      if (!isPrivate) {
        arrayList.add(player);
      }

      playerMap.put(activeServer, arrayList);

    }

    if (isPrivate) {
      // Add player to each server. The plugin will handle false players;
      for (String player : players) {
        if (PlayerHandler.INSTANCE.getPlayerFromPlayerName(player).isIgnoring(sender) && !senderIsModerator) {
          continue;
        }

        for (int server : playerMap.keySet()) {
          playerMap.get(server).add(player);
        }
      }
    }

    for (int server : playerMap.keySet()) {
      ChannelMessage e = isPrivate ? 
          new ChannelMessage(channelName, senderPrefix, sender, playerMap.get(server), message, channelPrefixEnabled) : 
          new ChannelMessage(prefix, senderPrefix, sender, playerMap.get(server), message, channelPrefixEnabled);

      e.setSenderIsModerator(senderIsModerator);

      Client handler = Clients.INSTANCE.getClientFromInt(server);
      ClientWriter writer = handler.getWriter();
      writer.addToQueue(e);
    }
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public CopyOnWriteArrayList<String> getPlayers() {
    return players;
  }

  public void setPlayers(CopyOnWriteArrayList<String> players) {
    this.players = players;
  }

  public boolean isChannelPrefixEnabled() {
    return channelPrefixEnabled;
  }

  public void setChannelPrefixEnabled(boolean channelPrefixEnabled) {
    this.channelPrefixEnabled = channelPrefixEnabled;
  }

  public CopyOnWriteArrayList<String> getOwners() {
    return owners;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean hasLogData() {
    return !channelLog.isEmpty();
  }

  public ArrayList<ChatMessage> getQueue() {
    ArrayList<ChatMessage> toBeSaved = new ArrayList<ChatMessage>();
    while (!channelLog.isEmpty()) {
      toBeSaved.add(channelLog.poll());
    }

    return toBeSaved;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
