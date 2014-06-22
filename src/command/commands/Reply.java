package command.commands;

import main.logging.Logger;
import packets.CommandMessage;
import packets.PlayerMessage;
import chat.ChannelHandler;
import chat.PrivateChannelMap;
import command.Command;

public class Reply extends Command{

  public Reply(CommandMessage message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object execute() {
    String openPrivateChannel = PrivateChannelMap.INSTANCE.checkForOpenChannel(message.getSender());
    Logger.log("Open Private Channel: " + openPrivateChannel, Logger.DEBUG);
    
    if(message.getArgs().length == 0){
      if(openPrivateChannel != null){
        ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(message.getSender(), openPrivateChannel);
        
        String recievingPlayer = ChannelHandler.INSTANCE.getChannelFromChannelName(openPrivateChannel).getPlayerThatIsNot(message.getSender());
        if(recievingPlayer != null){
          return new PlayerMessage(message.getSender(), "&7[&2*&7] Opened a channel with &b" + recievingPlayer);
        } else {
          return new PlayerMessage(message.getSender(), "&7[&4*&7] Player not available");
        }

      }
      return new PlayerMessage(message.getSender(), "&7[&4*&7] No open channel");
    } else { // /r <message>
      String messages = "";
      for(int i = 0; i < message.getArgs().length; i++){
        messages += message.getArgs()[i] + " ";
      }
      
      if(openPrivateChannel != null){
        ChannelHandler.INSTANCE.getChannelFromChannelName(openPrivateChannel).SendMessage(messages, null, message.getSender(), false);
        return null;
      }
      return new PlayerMessage(message.getSender(), "&7[&4*&7] No open channel");
    }
  }
}
