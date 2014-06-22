package chat.packetHandlers;

import chat.ChatMessageHandler;
import packets.ChatMessage;
import packets.Packet;
import main.Client;
import main.PacketHandler;
import main.logging.Logger;

public class ChatMessagePacketHandler extends PacketHandler {

  public ChatMessagePacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {

    Logger.log(packet.toString(), Logger.INFO);

    ChatMessageHandler handler = new ChatMessageHandler((ChatMessage) packet);
    return handler.handleMessage(client.getClientId());
  }

}
