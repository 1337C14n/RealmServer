package main.leetNetworkHandlers;

import command.CommandMessageHandler;

import packets.CommandMessage;
import packets.Packet;
import main.Client;
import main.PacketHandler;

public class CommandMessagePacketHandler extends PacketHandler{

  public CommandMessagePacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    CommandMessageHandler handler = new CommandMessageHandler((CommandMessage) packet);
    return handler.handle();
  }

}
