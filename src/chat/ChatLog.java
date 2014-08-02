package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.google.common.base.Throwables;

import configuration.Config;
import main.logging.Logger;

public class ChatLog implements Runnable{
  
  private Connection con;
  private String dataBase = Config.INSTANCE.getConfig().getDatabase();
  private String url = Config.INSTANCE.getConfig().getUrl();
  private String user = Config.INSTANCE.getConfig().getUser();
  private String password = Config.INSTANCE.getConfig().getPassword();
  private boolean connected = false;
  
  Channel channel;

  ChatLog(Channel channel){
    
    url = "jdbc:mysql://" + url + ":3306/" + dataBase;

    try {
      con = DriverManager.getConnection(url, user, password);
      setConnected(true);
    } catch (SQLException ex) {
      Logger.log(ex.getMessage(), Logger.CRITICAL);
      setConnected(false);
    }
    
    this.channel = channel;
  }

  @Override
  public void run() {
    while(true){
      try {
        if(channel.hasLogData()){
          //writeTextFile(outPutFile, channel.getQueue());
          try {
            writeMySQL(channel.getQueue());
          } catch (SQLException e) {
            log(Throwables.getStackTraceAsString(e));
          }
        }
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
  }
  
  void writeMySQL(List<ChatMessage> entities) throws SQLException{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO chatlog (channel, player, text, time) VALUES (?, ?, ?, ?);");
    for (int i = 0; i < entities.size(); i++) {
        ChatMessage entity = entities.get(i);
        
        statement.setString(1, channel.getChannelName());
        statement.setString(2, entity.getPlayer());
        statement.setString(3, entity.getText());
        statement.setTimestamp(4, new java.sql.Timestamp(entity.getTime().getTime()));
        statement.addBatch();
    }
    statement.executeBatch();
  }
  
  private void connect() {
    url = "jdbc:mysql://" + url + ":3306/" + dataBase;

    try {
      con = DriverManager.getConnection(url, user, password);
      setConnected(true);
    } catch (SQLException ex) {
      Logger.log(ex.getMessage(), Logger.CRITICAL);
      setConnected(false);
    }
  }
  
  public Connection getConnection() {

    if (con == null) {
      connect();
    } else {
      try {
        if (!con.isValid(1)) {
          log("Connection stale. Reconnecting");
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
  
  public void log(String message){
    Logger.log("[ChatLogger - " + channel.getName() + "] " + message, Logger.INFO);
  }

}
