package packets;

public class PlayerProxyPacket extends NetworkPacket{

  private static final long serialVersionUID = 1L;
  
  private String name;
  /*
   * Signifies that a player is transferring to another server
   */
  public PlayerProxyPacket(String name){
    this.name = name;
  }
  public String getName() {
    return name;
  }

}
