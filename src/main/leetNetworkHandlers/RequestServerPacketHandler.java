package main.leetNetworkHandlers;

import packets.Packet;
import packets.PlayerMessage;
import packets.RequestServer;
import main.AsycPacketHandler;
import main.Client;
import main.Clients;
import main.DataBaseConnector;
import main.logging.Logger;
import minigame.MiniGame;
import minigame.MiniGameRegistry;
import minigame.MiniGameStatus;

/**
 * Handles the Packet RequestServer
 * 
 * The incoming RequestServer packet will have a player name and a null server
 * name.
 * 
 * When handle is called.. We will check to see what server the player is
 * currently active on. That server will be cross referenced with the list of
 * MiniGames. If that server is a MiniGame server we will get the current status
 * of the MiniGame. When that MiniGame status is STARTED we will ask that server
 * if the player is allowed to connect to it. If the server responds will false
 * we will redirect that player to the hub. A Request server packet will then be
 * returned with the server the player should be connected to.
 */
public class RequestServerPacketHandler extends AsycPacketHandler {

  public RequestServerPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    String packetPlayerName = ((RequestServer) packet).getPlayerName();
    String packetServerName = ((RequestServer) packet).getServerName();

    /*
     * This is only when a Minecraft server is requesting for a player to be
     * moved. forward to the proxy server.
     */
    Logger.info("Forward Reqest for player: " + packetPlayerName + " to server: " + packetServerName);
    
    MiniGame game = MiniGameRegistry.INSTANCE.GetMiniGameFromServerName(packetServerName);
    
    if(game == null){
      return new PlayerMessage(packetPlayerName, "&7[&4*&7] &7Server unavailable");
    }
    
    Logger.info(packetServerName + " " + game.getStatus().getStatusMessage());
    
    if (game.getStatus() != MiniGameStatus.OPEN && game.getStatus() != MiniGameStatus.WAITING_FOR_PLAYERS){
      return new PlayerMessage(packetPlayerName, "&7[&4*&7] &7" + game.getStatus().getStatusMessage());
    }
    
    //Request bungee client
    Client bungee = Clients.INSTANCE.getClientFromName("BungeeCord");
    
    //Set player's active server to server name
    DataBaseConnector.INSTANCE.setPlayerServer(packetPlayerName, ((RequestServer) packet).getServerName());
    
    if (bungee != null) {
      bungee.getWriter().addToQueue(packet);
    } else {
      Logger.critical("Bungee Server Missing");
    }

    return null;
  }
}
