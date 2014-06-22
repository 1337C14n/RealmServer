package main.logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Throwables;

import configuration.Config;

public class LogConsumer implements Runnable{
  
  private Connection con;
  private String dataBase = Config.INSTANCE.getConfig().getDatabase();
  private String url = Config.INSTANCE.getConfig().getUrl();
  private String user = Config.INSTANCE.getConfig().getUser();
  private String password = Config.INSTANCE.getConfig().getPassword();
  private boolean connected = false;
  
  /*final static String OUTPUT_FOLDER_NAME = "/";
  final static Charset ENCODING = StandardCharsets.UTF_8;
  String outPutFile;
  String current;*/
  
  public LogConsumer(){
    url = "jdbc:mysql://localhost:3306/" + dataBase;

    try {
      con = DriverManager.getConnection(url, user, password);
      setConnected(true);
    } catch (SQLException ex) {
      Logger.log(ex.getMessage(), Logger.CRITICAL);
      setConnected(false);
    }
    
    /*try {
       current = new java.io.File( "." ).getCanonicalPath();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
    }
    this.outPutFile = current + OUTPUT_FOLDER_NAME + "serverlog.txt";*/
  }

  @Override
  public void run() {
    while(true){
      try {
        if(!Logger.logQueue.isEmpty()){
          ArrayList<LoggedEvent> toBeSaved = new ArrayList<LoggedEvent>();
          while(!Logger.logQueue.isEmpty()){
            toBeSaved.add(Logger.logQueue.poll());
          }
          //writeTextFile(outPutFile, toBeSaved);
          try {
            writeMySQL(toBeSaved);
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
  
  /*void writeTextFile(String outPutFile, List<String> aLines) throws IOException {
    
    try (PrintWriter writer = new PrintWriter( new BufferedWriter(new FileWriter(outPutFile, true)))){
      for(String line : aLines){
        writer.println(line);
      }
      writer.close();
    }
  }*/
  
  void writeMySQL(List<LoggedEvent> entities) throws SQLException{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO log (level, text, date) VALUES (?, ?, ?);");
    for (int i = 0; i < entities.size(); i++) {
        LoggedEvent entity = entities.get(i);
        
        statement.setString(1, entity.getLevel().getLevel());
        statement.setString(2, entity.getText());
        statement.setTimestamp(3, new java.sql.Timestamp(entity.getDate().getTime()));
        statement.addBatch();
    }
    statement.executeBatch();
  }
  
  private void connect() {
    url = "jdbc:mysql://localhost:3306/" + dataBase;

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
    Logger.log("[Logger]: " + message, Logger.INFO);
  }
}
