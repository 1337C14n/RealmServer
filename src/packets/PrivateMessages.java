package packets;

//NOT IMPLEMENTED YET!!! INCOMPLETE!!!

public class PrivateMessages extends ChatPacket {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1355620907188389068L;

  private String sender;
  private String recipient;
  private String message;
  private boolean senderIsModerator = false;

  public PrivateMessages(String player1, String player2, String message) {
    this.sender = player1;
    this.recipient = player2;
    this.message = message;
  }

  @Override
  public String toString() {
    return "<" + sender + ">: " + message;
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

  public boolean isSenderModerator() {
    return senderIsModerator;
  }

  public void setSender(String player1) {
    this.sender = player1;
  }

  public void setRecipient(String player2) {
    this.recipient = player2;
  }

  public void setSenderIsModerator(boolean senderIsModerator) {
    this.senderIsModerator = senderIsModerator;
  }
}
