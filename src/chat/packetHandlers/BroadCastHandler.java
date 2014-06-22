package chat.packetHandlers;

import packets.Packet;
import main.Client;
import main.Clients;
import main.PacketHandler;

public class BroadCastHandler extends PacketHandler {

  public BroadCastHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    for (Client client : Clients.INSTANCE.getClients()) {
      client.getWriter().addToQueue(packet);
    }
    return null;
  }

}
