package packets;

/**
 * Allows for the realm server to change a players server
 */
public class RedirectPacket extends NetworkPacket{

  private static final long serialVersionUID = 1426579487683978547L;
  
  String player, serverName;
  
  public RedirectPacket(String player, String serverName){
    this.serverName = serverName;
    this.player = player;
  }

  public String getPlayer() {
    return player;
  }

  public String getServerName() {
    return serverName;
  }

}
