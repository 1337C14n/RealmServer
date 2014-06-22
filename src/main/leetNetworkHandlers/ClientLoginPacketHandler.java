package main.leetNetworkHandlers;

import packets.Packet;
import main.Client;
import main.PacketHandler;

public class ClientLoginPacketHandler extends PacketHandler{

  public ClientLoginPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    /* This is a client lets add it to out list of active clients
    // What type of client is it?
    String type = ((ClientLogin) packet).getType();
    String name = ((ClientLogin) packet).getName();
    
    Logger.log("Client Logged in Type: " + type + " Name: " + name , Logger.INFO);
    
    //Register the client with the clients table
    Clients.INSTANCE.chatClients.put(client.getClientId(), type);
    Clients.INSTANCE.serverNames.put(name, client.getClientId());
    
    //Register mini game if it exists
    Logger.log("Mini Game Registered: " + type , Logger.DEBUG);
    MiniGameRegistry.INSTANCE.putMiniGame(client, new MiniGame(name, type));
    */
    return null;
  }

}
