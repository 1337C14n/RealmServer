package packets;


public class ClientLogin extends NetworkPacket {
  
  /**
   * 
   */
  private static final long serialVersionUID = -946390141629966504L;
  String type;
  String name;
  
  public ClientLogin(String type, String name){
    this.type = type;
    this.name = name;
  }
  
  @Override
  public String toString(){
    return "Name: " + name + " Type: " + type;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }
}
