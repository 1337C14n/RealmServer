package command.commands;

import command.Command;

import main.DataBaseConnector;
import packets.CommandMessage;
import packets.PlayerMessage;

public class Unban extends Command{

  public Unban(CommandMessage message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object execute() {
    if(message.getArgs().length != 2){
      return new PlayerMessage(message.getSender(), "/unban <playerName>"); 
    } else {
      String player = message.getArgs()[0];
      String reason = message.getArgs()[1];
      
      DataBaseConnector.INSTANCE.banned(player, message.getSender(), false, null, reason);
    }
    return null;
  }

}
