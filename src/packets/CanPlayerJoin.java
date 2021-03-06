package packets;

/**
 * Used to ask a server if a player can join.
 */
public class CanPlayerJoin extends NetworkPacket{

  private static final long serialVersionUID = 6705590437150539185L;
  
  String playerName;
  boolean canJoin;
  
  public CanPlayerJoin(String playerName, boolean canJoin){
    this.playerName = playerName;
    this.canJoin = canJoin;
  }
  
  public String getPlayerName() {
    return playerName;
  }
  public boolean canJoin() {
    return canJoin;
  }

  public void setCanJoin(boolean canJoin) {
    this.canJoin = canJoin;
  }
}
