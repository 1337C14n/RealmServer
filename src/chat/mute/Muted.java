package chat.mute;

import java.util.concurrent.ConcurrentHashMap;

import main.PlayerHandler;

public enum Muted {
  INSTANCE;
  
  private ConcurrentHashMap<String, MuteThread> muteMap = new ConcurrentHashMap<>();
  
  public void addPlayer(String player){
    MuteThread thread = new MuteThread(player);
    
    Thread muteThread = new Thread(thread);
    muteThread.start();
  }
  
  public void removePlayer(String player){
    PlayerHandler.INSTANCE.getPlayerFromPlayerName(player).setUnmuteDate(null);
    if(muteMap.containsKey(player)){
      muteMap.get(player).cancel();
    }
  }
  
}
