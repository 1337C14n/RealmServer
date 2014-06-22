package chat.packetHandlers;

import java.util.ArrayList;

import chat.ChannelHandler;
import packets.ChannelList;
import packets.Packet;
import main.Client;
import main.PacketHandler;

public class ChannelListHandler extends PacketHandler{

  public ChannelListHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    if (((ChannelList) packet).getChannelLists() == null) {
      ArrayList<ArrayList<String>> channels = new ArrayList<ArrayList<String>>();
      for (String channelName : ChannelHandler.INSTANCE.getChannelListArray()) {
        ArrayList<String> channel = new ArrayList<String>();
        // Adding channel name to first element with in the array
        channel.add(channelName);
        // Following name of channel with players that are in the channel
        for (String player : ChannelHandler.INSTANCE.getChannelFromChannelName(channelName)
            .getPlayers()) {
          channel.add(player);
        }
        channels.add(channel);
      }
      return new ChannelList(channels);
    }
    return null;
  }

}
