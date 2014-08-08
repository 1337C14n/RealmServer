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
      return new PlayerMessage(message.getSender(), "&7[&4*&7] /unban <playerName>"); 
    } else {
      String player = message.getArgs()[0];
      String reason = message.getArgs()[1];
      
      if (DataBaseConnector.INSTANCE.isBanned(player)) {
        DataBaseConnector.INSTANCE.banned(player, message.getSender(), false, null, reason);
        return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is unbanned");
      } else {
        return new PlayerMessage(message.getSender(), "&7[&4*&7] " + player + " was not banned");
      }
      
    }

  }

}
