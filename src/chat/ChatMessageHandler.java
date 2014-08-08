package chat;

import main.Player;
import main.PlayerHandler;
import packets.ChatMessage;
import packets.Packet;
import packets.PlayerMessage;

public class ChatMessageHandler {
  private ChatMessage messagePacket;

  public ChatMessageHandler(ChatMessage messagePacket) {
    this.messagePacket = messagePacket;
  }

  public Packet handleMessage(int clientId) {
    String name = messagePacket.getName();
    String message = messagePacket.getMessage();
    String playerPrefix = messagePacket.getPlayerPrefix();
    boolean playerIsMod = messagePacket.isPlayerIsMod();

    Player player = PlayerHandler.INSTANCE.getPlayerFromPlayerName(name);

    if (player == null) {
      PlayerHandler.INSTANCE.playerLogin(name, clientId);
    }
    player = PlayerHandler.INSTANCE.getPlayerFromPlayerName(name);
    
    if (player.isMuted()) {
      return new PlayerMessage(name, "You are muted");
    }
    
    // check to see if player is currently in a pm;
    if(player.isInPrivateChat()){
      if(player.getPlayerTalkingTo() != null){
        PlayerHandler.INSTANCE.getPlayerFromPlayerName(player.getPlayerTalkingTo()).sendPrivateMessage(player, message);
        player.getClient().write(new PlayerMessage(player.getName(), "&7To " + player.getPlayerTalkingTo() + "&2: " + message));
      }

    }

    Channel channel = ChannelHandler.INSTANCE.getChannelFromChannelName(player.getActiveChannel());
    channel.SendMessage(message, playerPrefix, name, playerIsMod);
    
    return null;
  }
}
