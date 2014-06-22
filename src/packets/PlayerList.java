package packets;

import java.util.ArrayList;

public class PlayerList extends NetworkPacket {
  
  private static final long serialVersionUID = 3446224146094599102L;
  
  ArrayList<String> listOfPlayers = new ArrayList<String>();
  
  public PlayerList(ArrayList<String> listOfPlayers){
    this.listOfPlayers = listOfPlayers;
  }
  
  @Override
  public String toString(){
    return "listOfPlayers: " + listOfPlayers ;
  }

  public ArrayList<String> getPlayers() {
    return listOfPlayers;
  }
}
