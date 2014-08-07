package chat;

import java.util.concurrent.ConcurrentHashMap;

public enum PrivateChannelMap {
  INSTANCE;
  
  private ConcurrentHashMap<String, String> privateChannels = new ConcurrentHashMap<>();
  
  // PlayerName <----> channelName
  
  public String checkForOpenChannel(String playerName){
    return privateChannels.containsKey(playerName) ? privateChannels.get(playerName) : null;
  }
  
  public void addOpenChannel(String player, String channelName){
    privateChannels.put(player, channelName);
  }
  
  public void removeOpenChannel(String player){
    ChannelHandler.INSTANCE.removeChannel("_private_" + player);
  }
}
