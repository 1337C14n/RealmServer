package chat.packetHandlers;

import chat.ChatMessageHandler;
import packets.ChatMessage;
import packets.Packet;
import main.Client;
import main.PacketHandler;
import main.logging.Logger;


/**
 * This receives all packets that are chat message packets and creates a handler 
 * @author Michael
 *
 */
public class ChatMessagePacketHandler extends PacketHandler {

  public ChatMessagePacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {

    Logger.info(packet.toString());

    ChatMessageHandler handler = new ChatMessageHandler((ChatMessage) packet);
    return handler.handleMessage(client.getClientId());
  }

}
