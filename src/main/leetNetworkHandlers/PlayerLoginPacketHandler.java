package main.leetNetworkHandlers;

import main.Player;
import main.Client;
import main.Clients;
import main.PacketHandler;
import main.PlayerHandler;
import main.logging.Logger;
import packets.Broadcast;
import packets.Packet;
import packets.PlayerLogin;

public class PlayerLoginPacketHandler extends PacketHandler{

  public PlayerLoginPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    PlayerLogin player = (PlayerLogin) packet;
    
    Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player.getName());
    if (player.getIsLogged()) {
      
      if(chatPlayer != null){
        if(chatPlayer.isProxying()){
          Logger.log(player.getName() + " Proxied from another server", Logger.INFO);
          chatPlayer.setActiveServer(client.getClientId());
          chatPlayer.setProxying(false);
          return null;
        }
      }
      Logger.log(player.getName() + " Logged In", Logger.INFO);

      // Client Player Initialization
      Clients.INSTANCE.playerClient.put(player.getName(), client.getClientId());

      // Chat Player Initialization
      PlayerHandler.INSTANCE.playerLogin(player.getName(), client.getClientId());
      
      for(Client client : Clients.INSTANCE.getClients()){
        client.getWriter().addToQueue(new Broadcast("&7" + player.getName() + " logged in"));
      }
      
    } else {
      if(chatPlayer != null){
        if(!chatPlayer.isProxying()){
          Clients.INSTANCE.playerClient.remove(player.getName(), client.getClientId());
          PlayerHandler.INSTANCE.logOut(player.getName());
          
          Logger.log(player.getName() + " Logged Out", Logger.INFO);
          for(Client client : Clients.INSTANCE.getClients()){
            client.getWriter().addToQueue(new Broadcast("&7" + player.getName() + " logged out"));
          }
        } else {
          Logger.log(player.getName() + " transfering to another server", Logger.INFO);
        }
      }
    }
    return null;
  }

}
