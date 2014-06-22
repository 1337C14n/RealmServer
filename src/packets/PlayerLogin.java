package packets;


public class PlayerLogin extends NetworkPacket {
  /**
   * 
   */
  private static final long serialVersionUID = 5684261834110597983L;
  String name;
  boolean isLogged;
  
  public PlayerLogin(String name, boolean isLogged){
    this.name = name;
    this.isLogged = isLogged;
  }
  
  @Override
  public String toString(){
    return "Name: " + name;
  }
  
  public String getName() {
    return name;
  }
  
  public boolean getIsLogged(){
    return isLogged;
  }
}
