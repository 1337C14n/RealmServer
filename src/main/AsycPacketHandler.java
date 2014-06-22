package main;

import packets.Packet;

public abstract class AsycPacketHandler extends PacketHandler implements Runnable{
  
  public AsycPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public void run() {
    client.getWriter().addToQueue(this.handle());
  }

  @Override
  public abstract Packet handle();

}
