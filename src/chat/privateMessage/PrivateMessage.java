package chat.privateMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedQueue;
import main.DataBaseConnector;
import main.PlayerHandler;
import packets.PrivateMessagePacket;

//NOT IMPLEMENTED YET!!! INCOMPLETE!!!

/**
 * @author devore07
 *
 */

public class PrivateMessage {
	
	private int id;
	private String player1;
	private String player2;
	private boolean isOpen;
	
	ConcurrentLinkedQueue<PrivateChatMessage> privateLog = new ConcurrentLinkedQueue<PrivateChatMessage>();
	
	public PrivateMessage(int id, String player1, String player2, boolean isOpen) {
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
		this.isOpen = isOpen;
		
		/*
		 * An ID is needed from the database; This also registers it
		 * with the database;
		 */
		this.id = DataBaseConnector.INSTANCE.getPrivateMessageId(this);
		
		Thread clientThread = new Thread(new PrivateMessageLog(this));
		clientThread.start();
	}
	
	public void SendMessage(String message, String player1, String player2, boolean senderIsModerator) {
		
		Calendar cal = Calendar.getInstance();
		
		PrivateChatMessage privateChatMessage = new PrivateChatMessage(player1, player2, message, cal.getTime());
		
		privateLog.add(privateChatMessage);
		
		PlayerHandler.INSTANCE.getPlayerFromPlayerName(player2).getClient().write(new PrivateMessagePacket(player1, player2, message));
		
		/*
		 * if sender is sending a message to offline player, send it as mail (implement later);
		 * mail will be handled as /msg mail (to be implemented later!)
		 * else if recipient is online, send it to their client within a private message;
		 * 
		 * if sender is a mod, then bypass ignore if they're ignored;
		 */
		
		@SuppressWarnings("unused")
    String getSecondPerson = null; //gets second person IF they are online
		
		/*for(String secondPerson : players) {
			if
		}
		*/
	}
	
	public String getPlayer1() {
		return player1;
	}
	
	public String getPlayer2() {
		return player2;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	public boolean hasLogData() {
		return !privateLog.isEmpty();
	}
	
	public ArrayList<PrivateChatMessage> getQueue() {
		ArrayList<PrivateChatMessage> toBeSaved = new ArrayList<PrivateChatMessage>();
		while(!privateLog.isEmpty()) {
			toBeSaved.add(privateLog.poll());
		}
		
		return toBeSaved;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}
