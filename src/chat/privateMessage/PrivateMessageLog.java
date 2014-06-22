package chat.privateMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.google.common.base.Throwables;

import main.logging.Logger;
import configuration.Config;

//NOT IMPLEMENTED YET!!! INCOMPLETE!!!

/**
 * @author devore07
 *
 */

public class PrivateMessageLog implements Runnable {
	
	private Connection con;
	private String dataBase = Config.INSTANCE.getConfig().getDatabase();
	private String url = Config.INSTANCE.getConfig().getUrl();
	private String user = Config.INSTANCE.getConfig().getUser();
	private String password = Config.INSTANCE.getConfig().getPassword();
	private boolean connected = false;
	
	PrivateMessage privateMessage;
	
	PrivateMessageLog(PrivateMessage privateMessage) {
		
		url = "jdbc:mysql://localhost:3306/" + dataBase;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			setConnected(true);
		} catch(SQLException ex) {
			Logger.log(ex.getMessage(), Logger.CRITICAL);
			setConnected(false);
		}
		
		this.privateMessage = privateMessage;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(privateMessage.hasLogData()) {
					try {
						writeMySQL(privateMessage.getQueue());
					} catch(SQLException e) {
						log(Throwables.getStackTraceAsString(e));
					}
				}
				Thread.sleep(10000);
			} catch(InterruptedException e) {
				Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
			}
		}
	}
	
	void writeMySQL(List<PrivateChatMessage> entities) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement("INSERT INTO privateMessageLog (player1, player2, text, time) VALUES (?, ?, ?, ?);");
		for(int i=0; i<entities.size();i++) {
			PrivateChatMessage entity = entities.get(i);
			
			statement.setString(1, entity.getSender());
			statement.setString(2, entity.getRecipient());
			statement.setString(3, entity.getMessage());
			statement.setTimestamp(4, new java.sql.Timestamp(entity.getTime().getTime()));
			statement.addBatch();
		}
		statement.executeBatch();
	}
	
	private void connect() {
		url = "jdbc:mysql://localhost:3306/" + dataBase;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			setConnected(true);
		} catch(SQLException ex) {
			Logger.log(ex.getMessage(), Logger.CRITICAL);
			setConnected(false);
		}
	}
	
	public Connection getConnection() {
		
		if(con == null) {
			connect();
		} else {
			try {
				if(!con.isValid(1)) {
					log("Connection stale. Reconnecting");
					con.close();
				}
				
				if(con.isClosed()) {
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
	
	public void log(String message) {
		Logger.log("[PrivateMessageLogger - Player1:" + privateMessage.getPlayer1() + " Recipient: " + privateMessage.getPlayer2() + "] " + message, Logger.INFO);
	}
}
