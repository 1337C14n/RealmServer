package main.leetNetworkHandlers;

import packets.CanPlayerJoin;
import packets.Packet;
import main.Client;
import main.PacketHandler;

/**
 * Handles the response from a server if a player can join.
 * 
 * We will add the response to the synchronizer and notify the wait object
 *
 */
public class CanPlayerJoinPacketHandler extends PacketHandler {

  public CanPlayerJoinPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    String playerName = ((CanPlayerJoin)packet).getPlayerName();
    boolean canJoin = ((CanPlayerJoin)packet).canJoin();
    
    ServerRequestSynchronizer.INSTANCE.responses.put(playerName, canJoin);
    
    synchronized(ServerRequestSynchronizer.INSTANCE.waitObjects.get(playerName)){
      ServerRequestSynchronizer.INSTANCE.waitObjects.get(playerName).notify();
    }
    
    return null;
  }

}
