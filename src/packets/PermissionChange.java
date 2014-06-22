package packets;

import java.util.ArrayList;

public class PermissionChange extends NetworkPacket{

  private static final long serialVersionUID = 1L;
  
  private ArrayList<String> players;
  
  public PermissionChange(ArrayList<String> players){
    this.players = players;
  }
  
  public PermissionChange(String player){
    players = new ArrayList<String>();
    players.add(player);
  }
  
  public ArrayList<String> getPlayers(){
    return this.players;
  }

}
