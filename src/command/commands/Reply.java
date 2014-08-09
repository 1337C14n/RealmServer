package command.commands;

import main.PlayerHandler;
import packets.CommandMessage;
import packets.PlayerMessage;
import command.Command;

public class Reply extends Command{

  public Reply(CommandMessage message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object execute() {
    if(sender == null){
      return null;
    }
    if(sender.getLastPMFrom() == null){
      return new PlayerMessage(message.getSender(), "&7[&4*&7] No open channel");
    }
    if(PlayerHandler.INSTANCE.getPlayerFromPlayerName(sender.getLastPMFrom()) == null){
      return new PlayerMessage(message.getSender(), "&7[&4*&7] Player not online");
    }
    
    if(message.getArgs().length == 0){    
      
        sender.setInPrivateChat(true);
        sender.setPlayerTalkingTo(sender.getLastPMFrom());
        return new PlayerMessage(message.getSender(), "&7[&2*&7] Opened a channel with &b" + sender.getLastPMFrom());
        
    } else { // /r <message>
      String messages = "";
      for(int i = 0; i < message.getArgs().length; i++){
        messages += message.getArgs()[i] + " ";
      }
      
      PlayerHandler.INSTANCE.getPlayerFromPlayerName(sender.getLastPMFrom()).sendPrivateMessage(sender, messages);
      return new PlayerMessage(sender.getName(), "&7To " + sender.getLastPMFrom() + "&2: " + messages);
    }
  }
}
