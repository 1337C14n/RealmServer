package packets;

public class Kick extends NetworkPacket {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  String playerName, message;
  
  public Kick(String playerName, String message){
    this.playerName = playerName;
    this.message = message;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  

}
