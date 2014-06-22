package main.leetNetworkHandlers;

import packets.Packet;
import packets.PlayerProxyPacket;
import main.Client;
import main.PacketHandler;
import main.PlayerHandler;

public class PlayerProxyPacketHandler extends PacketHandler{

  public PlayerProxyPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    /*
     *  Signifies that a player will be transferring to another server
     *  We will ignore the next player logout packet
     *  The next player login will set the player to that server.
     */
    String player = ((PlayerProxyPacket)packet).getName();
    
    PlayerHandler.INSTANCE.getPlayerFromPlayerName(player).setProxying(true);
    
    return null;
  }
  
}
