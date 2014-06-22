package chat.privateMessage;

import java.util.Date;

//NOT IMPLEMENTED YET!!! INCOMPLETE!!!

/**
 * @author devore07
 *
 */

public class PrivateChatMessage {
	private String sender;
	private String recipient;
	private String message;
	private Date time;
	
	public PrivateChatMessage(String sender, String recipient, String message, Date time) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.time = time;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getTime() {
		return time;
	}	
}
