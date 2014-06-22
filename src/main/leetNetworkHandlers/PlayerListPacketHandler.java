package main.leetNetworkHandlers;

import java.util.ArrayList;

import packets.Packet;
import packets.PlayerList;
import main.Player;
import main.Client;
import main.Clients;
import main.PacketHandler;
import main.PlayerHandler;
import main.logging.Logger;

public class PlayerListPacketHandler extends PacketHandler{

  public PlayerListPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    if (((PlayerList) packet).getPlayers() == null) {
      ArrayList<String> listOfPlayers = new ArrayList<String>();
      for (Player chatPlayer : PlayerHandler.INSTANCE.getChatPlayers()) {
        listOfPlayers.add(chatPlayer.getName());
      }
      return new PlayerList(listOfPlayers);
    } else {
      for (String player : ((PlayerList) packet).getPlayers()) {
        Logger.log("Existing Player: " + player, Logger.INFO);

        // Client Player Initialization
        Clients.INSTANCE.playerClient.put(player, client.getClientId());

        // Chat Player Initialization
        PlayerHandler.INSTANCE.playerLogin(player, client.getClientId());
      }
    }
    return null;
  }

}
