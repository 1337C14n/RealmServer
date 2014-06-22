package packets;


public class HeartBeat extends NetworkPacket {
  
  private static final long serialVersionUID = -7003879098341552985L;
  
  boolean active;
  
  public HeartBeat(boolean active){
    this.active = active;
  }
}
