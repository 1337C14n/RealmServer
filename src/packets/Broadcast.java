package packets;


public class Broadcast extends ChatPacket{

  private static final long serialVersionUID = -3885021367376805264L;
  
  private String message;
  
  public Broadcast(String message){
    this.message = message;
  }
  
  public String getMessage() {
    return message;
  }
}
