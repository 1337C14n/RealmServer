package main;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import packets.Broadcast;

public enum Clients {
  INSTANCE;
  ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();
  public ConcurrentHashMap<String, Integer> playerClient = new ConcurrentHashMap<String, Integer>();
  
  public ConcurrentHashMap<Integer, String> chatClients = new ConcurrentHashMap<Integer, String>(); // External Chant Clients
  
  public ConcurrentHashMap<String, Integer> serverNames = new ConcurrentHashMap<>();
  
  AtomicInteger masterClient = new AtomicInteger(0);
  
  public Client getClientFromInt(int clientId){
    return clients.get(clientId);
  }
  
  public void removeChatClient(int clientId){
    if(chatClients.containsKey(clientId)){
      chatClients.remove(clientId);
    }
  }
  
  public ArrayList<Integer> getChatClients(){
    ArrayList<Integer> connectedClients = new ArrayList<Integer>();
    for(int i : chatClients.keySet()){
      connectedClients.add(i);
    }
    return connectedClients;
  }
  
  public int getMaster(){
  	return masterClient.get();
  }
  
  public void setMaster(int clientNumber){
  	masterClient.set(clientNumber);
  }
  
  public ArrayList<Client> getClients(){
  	ArrayList<Client> handlers = new ArrayList<>();
  	
  	for(int key : clients.keySet()){
  		handlers.add(clients.get(key));
  	}
  	
  	return handlers;
  }
  
  public void disposeClient(int clientId){
  	if(clients.containsKey(clientId)){
  		clients.remove(clientId);
  	}
  	if(chatClients.containsKey(clientId)){
  		chatClients.remove(clientId);
  	}
  }
  
  public void broadcast(String message){
    for(Client handler : getClients()){
      handler.getWriter().addToQueue(new Broadcast(message));
    }
  }
  
  public Client getClientFromName(String name){
    if(serverNames.containsKey(name)){
      if(this.clients.containsKey(serverNames.get(name))){
        return clients.get(serverNames.get(name));
      }
    }
    return null;
  }
}
