package main;

import packets.Packet;

public abstract class PacketHandler {
  protected Packet packet;
  protected Client client;

  /**
   * Handles received packets
   * @param packet Packet to be handled
   * @param client ClientHandler that is handling the client
   */
  public PacketHandler(Packet packet, Client client) {
    this.packet = packet;
    this.client = client;
  }
  
  /**
   * Handles The packet
   * @return Response from handled packet
   */
  public abstract Packet handle();
}
