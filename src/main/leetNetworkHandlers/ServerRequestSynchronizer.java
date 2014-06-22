package main.leetNetworkHandlers;

import java.util.concurrent.ConcurrentHashMap;

public enum ServerRequestSynchronizer {
  INSTANCE;
  
  public ConcurrentHashMap<String, Object> waitObjects;
  public ConcurrentHashMap<String, Boolean> responses;
  
  ServerRequestSynchronizer(){
    waitObjects = new ConcurrentHashMap<>();
  }
  
  public void addWaitObject(String playerName){
    Object waitObject = new Object();
    waitObjects.put(playerName, waitObject);
  }
  
  public void addResponse(String playerName, boolean canJoin){
    responses.put(playerName, canJoin);
  }
  
  public boolean canJoin(String playerName){
    if(responses.containsKey(playerName)){
      return responses.get(playerName);
    } else {
      return false;
    }
  }
}
