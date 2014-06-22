package main.leetNetworkHandlers;

import packets.HeartBeat;
import packets.Packet;
import main.Client;
import main.PacketHandler;

public class HeartBeatHandler extends PacketHandler{

  public HeartBeatHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    return new HeartBeat(true);
  }

}
