package minigame;

import java.util.concurrent.ConcurrentHashMap;

import main.Client;

public enum MiniGameRegistry {
  INSTANCE;
  
  //Maps Server to miniGame
  private ConcurrentHashMap<Integer, MiniGame> miniGames = new ConcurrentHashMap<>();
  
  public void putMiniGame(Client handler, MiniGame miniGame){
    miniGames.put(handler.getClientId(), miniGame);
  }
  
  public MiniGame getMiniGame(Client handler){
    return miniGames.containsKey(handler.getClientId()) ?  miniGames.get(handler.getClientId()) : null;
  }
  
  public String getListOfType(String type){
    
    String list = "";
    
    for(Integer key : miniGames.keySet()){
      MiniGame game = miniGames.get(key);
      if(type.equals(game.getType())){
        list += "[&" + (game.getStatus().getStatus() + 1) + "*" + "&f] " + "Server: " + game.getServerName() + "\n";
      }
    }
    
    return list;
  }
  
  public String getOpenServerOfType(String type){
    for(Integer key : miniGames.keySet()){
      MiniGame game = miniGames.get(key);
      if(type.equals(game.getType()) && game.getStatus() == MiniGameStatus.WAITING_FOR_PLAYERS){
        return game.getServerName();
      }
    }
    return null;
  }
  
  public void removeServer(Client handler){
    int serverNum = handler.getClientId();
    if(miniGames.containsKey(serverNum)){
      miniGames.remove(serverNum);
    }
  }
  
  public boolean miniGameExists(String serverName){
    for(Integer gameNumber : miniGames.keySet()){
      if(miniGames.get(gameNumber).getServerName().equalsIgnoreCase(serverName)){
        return true;
      }
    }
    return false;
  }
  
  public int getMiniGameInt(String serverName){
    for(int server: miniGames.keySet()){
      MiniGame miniGame = miniGames.get(server);
      if(serverName.equals(miniGame.getServerName())){
        return server;
      }
    }
    return -1;
  }
  
  public MiniGame GetMiniGameFromServerName(String serverName){
    try{
      for(int server: miniGames.keySet()){
        MiniGame miniGame = miniGames.get(server);
        if(serverName.equalsIgnoreCase(miniGame.getServerName())){
          return miniGame;
        }
      }
    } catch (NullPointerException e){
      return null;
    }

    return null;
  }
}
