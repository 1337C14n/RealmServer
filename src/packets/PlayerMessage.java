package packets;


public class PlayerMessage extends ChatPacket {

  /**
   * Sends Message Directly to the player.
   * Meant for response to a command or an update
   */
  private static final long serialVersionUID = 6459610660372664843L;

  private String name;
  private String message;
  
  public PlayerMessage(String name, String message){
    this.name = name;
    this.message = message;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
