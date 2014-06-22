package packets;

import java.io.Serializable;
import java.util.ArrayList;

public class ChannelList extends ChatPacket implements Serializable{
  
  private static final long serialVersionUID = -1893705367785305095L;
  
  ArrayList<ArrayList<String>> listOfPlayers = new ArrayList<ArrayList<String>>();
  
  public ChannelList(ArrayList< ArrayList<String>> listOfPlayers){
    this.listOfPlayers = listOfPlayers;
  }
  
  public ArrayList< ArrayList<String>> getChannelLists() {
    return listOfPlayers;
  }
}
