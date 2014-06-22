package packets;

import minigame.MiniGameStatus;

public class MiniGameStatusPacket extends NetworkPacket{

  /**
   * 
   */
  private static final long serialVersionUID = -7407456485546339392L;
  
  MiniGameStatus status;
  
  public MiniGameStatusPacket(MiniGameStatus status){
    this.status = status;
  }

  public MiniGameStatus getStatus() {
    return status;
  }

}
