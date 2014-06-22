package packets;

public class RequestServer extends Packet{

  /**
   * 
   */
  private static final long serialVersionUID = -8605272023712619517L;
  
  private String playerName;
  private String serverName;
  private boolean isBanned;
  String banReason;
  
  public RequestServer(String playerName, String serverName){
    this.playerName = playerName;
    this.serverName = serverName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  public boolean isBanned() {
    return isBanned;
  }

  public void setBanned(boolean isBanned) {
    this.isBanned = isBanned;
  }

  public String getBanReason() {
    return banReason;
  }

  public void setBanReason(String banReason) {
    this.banReason = banReason;
  }

}
