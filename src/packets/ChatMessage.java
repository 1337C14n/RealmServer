package packets;


public class ChatMessage extends ChatPacket {

  /**
   * 
   */
  private static final long serialVersionUID = -8941003061386845348L;
  
  private String name;
  private String message;
  private String playerPrefix;
  private boolean playerIsMod;
  
  public ChatMessage(String name, String playerPrefix, String message){
    this.name = name;
    this.message = message;
    this.playerPrefix = playerPrefix;
  }
  
  @Override
  public String toString(){
    return "<" + name +">: " + message;
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

  public String getPlayerPrefix() {
    return playerPrefix;
  }

  public boolean isPlayerIsMod() {
    return playerIsMod;
  }

  public void setPlayerIsMod(boolean playerIsMod) {
    this.playerIsMod = playerIsMod;
  }
}
