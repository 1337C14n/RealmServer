package main.leetNetworkHandlers;

import packets.MiniGameStatusPacket;
import packets.Packet;
import main.Client;
import main.Clients;
import main.DataBaseConnector;
import main.PacketHandler;
import main.logging.Logger;
import minigame.MiniGame;
import minigame.MiniGameRegistry;
import minigame.MiniGameStatus;

public class MiniGameStatusPacketHandler extends PacketHandler{

  public MiniGameStatusPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    //Incoming update from mini game
    
    MiniGameStatus status = ((MiniGameStatusPacket)packet).getStatus();
    
    if(status == null){

      Logger.log("Mini Games Status: NULL", Logger.DEBUG);
      return null;
      
    }

    Logger.log("Mini Games Status: " + status.getStatus(), Logger.DEBUG);
    
    //update status to what ever status was sent by the client
    
    MiniGame game = MiniGameRegistry.INSTANCE.getMiniGame(client);
    
    if(game == null){
      Logger.log("Mini game was null", Logger.DEBUG);
      return null;
    }
    
    //Set the new status
    game.setStatus(status);
    
    MiniGameRegistry.INSTANCE.putMiniGame(client, game);
    
    Logger.log("New Status: " +  status.getStatus(), Logger.DEBUG);
    
    if(status == MiniGameStatus.WAITING_FOR_PLAYERS){
      Clients.INSTANCE.broadcast("&7[&b*&7] Game server " + game.getServerName() + " is ready");
      DataBaseConnector.INSTANCE.registerServer(game.getServerName()); //Register server with database so players can connect.
    }
    return null;
  }

}
