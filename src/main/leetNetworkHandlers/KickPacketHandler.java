package main.leetNetworkHandlers;

import main.Client;
import main.DataBaseConnector;
import main.PacketHandler;
import packets.Kick;
import packets.Packet;

public class KickPacketHandler extends PacketHandler{

  public KickPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    String player = ((Kick) packet).getPlayerName();
    String message = ((Kick) packet).getMessage();

    if (message == null) {
      if (DataBaseConnector.INSTANCE.isBanned(player)) {
        return new Kick(player, "You are banned");
      } else {
        return new Kick(player, null);
      }
    }
    return null;
  }

}
