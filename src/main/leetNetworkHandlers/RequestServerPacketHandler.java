package main.leetNetworkHandlers;

import packets.CanPlayerJoin;
import packets.Packet;
import packets.RequestServer;
import main.AsycPacketHandler;
import main.Client;
import main.Clients;
import main.DataBaseConnector;
import main.PlayerHandler;
import main.logging.Logger;
import minigame.MiniGame;
import minigame.MiniGameRegistry;
import minigame.MiniGameStatus;

/**
 * Handles the Packet RequestServer
 * 
 * The incoming RequestServer packet will have a player name and a null server name.
 * 
 * When handle is called..
 * We will check to see what server the player is currently active on. That server will be cross 
 * referenced with the list of MiniGames. If that server is a MiniGame server we will get the current
 * status of the MiniGame. When that MiniGame status is STARTED we will ask that server if the player is allowed
 * to connect to it. If the server responds will false we will redirect that player to the hub. 
 * A Request server packet will then be returned with the server the player should
 * be connected to.
 */
public class RequestServerPacketHandler extends AsycPacketHandler {

  public RequestServerPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    String packetPlayerName = ((RequestServer) packet).getPlayerName();
    String packetServerName = ((RequestServer) packet).getServerName();
    
    Logger.log("Player Name: " + packetPlayerName, Logger.DEBUG);
    Logger.log("Server Name: " + packetServerName, Logger.DEBUG);
    
    // OnRequest the server name should be null.
    if (packetServerName.equals("WORLDREQUEST")) {
      if (DataBaseConnector.INSTANCE.isBanned(packetPlayerName) || PlayerHandler.INSTANCE.banCacheContains(packetPlayerName)) {
        RequestServer server = new RequestServer(packetPlayerName, "hub");
        server.setBanned(true);
        server.setBanReason("You are banned");
        return server;
      } 
      
      Logger.log("Request for server Player: " + packetPlayerName, Logger.DEBUG);
      //Get the last server the player was seen on
      String serverName = DataBaseConnector.INSTANCE.getPlayerServer(packetPlayerName);

      MiniGame game = MiniGameRegistry.INSTANCE.GetMiniGameFromServerName(serverName);
      //Check to see if that server is a MiniGames server. 
      if (game != null) {
        //The MiniGames does exist. Lets get the server the MiniGames is running on.
        
        Logger.log("MiniGame status: " + game.getStatus().getStatus(), Logger.DEBUG);
        
        if(game.getStatus() == MiniGameStatus.STARTED){
          /*
           * Check to see if the player is allowed to connect to the mini game
           * if they can connect.. connect them else forward them to the hub
           */   
          ServerRequestSynchronizer.INSTANCE.addWaitObject(packetPlayerName);
          
          //Ask the server is a given player can join 
          client.getWriter().addToQueue(new CanPlayerJoin(packetPlayerName, false));
          
          synchronized(ServerRequestSynchronizer.INSTANCE.waitObjects.get(packetPlayerName)){
            try {
              ServerRequestSynchronizer.INSTANCE.waitObjects.get(packetPlayerName).wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          
          if(ServerRequestSynchronizer.INSTANCE.canJoin(packetPlayerName)){
            Logger.log("Game Started and player can join", Logger.DEBUG);
            return new RequestServer(packetPlayerName, serverName);
          } else {
            Logger.log("Game Started and player cannot join", Logger.DEBUG);
            return new RequestServer(packetPlayerName, "hub");
          }
        } else if (game.getStatus() == MiniGameStatus.OPEN){
          /*
           * This is server that a player does not need permission to enter. Connect Them
           */
          Logger.log("Game is open", Logger.DEBUG);
          return new RequestServer(packetPlayerName, serverName);
        } else if (game.getStatus() == MiniGameStatus.WAITING_FOR_PLAYERS) {
          /*
           * The MiniGame is waiting for players it is ok to connect them.
           */
          Logger.log("Game is waiting for players", Logger.DEBUG);
          return new RequestServer(packetPlayerName, serverName);
        } else {
          /*
           * The MiniGame is not accepting new players. Connect the player to the hub
           */
          Logger.log("Game is not accepting new players", Logger.DEBUG);
          return new RequestServer(packetPlayerName, "hub");
        }
      } else {
        //The MiniGame does not exist. return to the hub
        Logger.log("Player: " + packetPlayerName 
          + " was trying to connect to a mini game that did not exist", Logger.WARNING);
        return new RequestServer(packetPlayerName, "hub");
      }
    } else {
      /*
       * This is only when a Minecraft server is requesting for a player to be moved.
       * forward to the proxy server.
       */
      Logger.log("Forwarding Server Change Request", Logger.DEBUG);
      Client bungee = Clients.INSTANCE.getClientFromName("BungeeCord");
      DataBaseConnector.INSTANCE.setPlayerServer(packetPlayerName, ((RequestServer) packet).getServerName());
      if(bungee != null){
        bungee.getWriter().addToQueue(packet);
      } else {
        Logger.log("Bungee Server Missing", Logger.WARNING);
      }
    }
    return null;
  }
}
