package command.commands;

import java.util.Calendar;

import command.Command;
import main.Player;
import main.DataBaseConnector;
import main.PlayerHandler;
import main.logging.Logger;
import packets.CommandMessage;
import packets.PlayerMessage;

public class Ban extends Command{

  public Ban(CommandMessage message) {
    super(message);
  }

  @Override
  public Object execute() {
    if(message.getArgs().length != 3){
      return new PlayerMessage(message.getSender(), "/ban <playerName> <time>"); 
    } 
    
    String player = message.getArgs()[0];
    String time = message.getArgs()[1];
    String reason = message.getArgs()[2];
    
    if (DataBaseConnector.INSTANCE.isBanned(player)) {
      return new PlayerMessage(message.getSender(), "&7[&4*&7] " + player + " is already banned");
    }
    
    if (time == null) {
      Logger.log("Banning Player: " + player + " with Reason: " + reason, Logger.MOD);
      
      Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
      if (chatPlayer != null) {
        chatPlayer.ban(message.getSender(), reason);
        return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now banned");
      } else {
        // Player is offline. We will attempt to ban player only if this
        // player is in the database.

        DataBaseConnector.INSTANCE.banned(player, message.getSender(), true, null, reason);

        Logger.mod("Banning Player: " + player + " with Reason: " + reason);
        
        return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now banned");
      
      }
    } else {

      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.SECOND, Integer.parseInt(time));

      Logger.mod("Banning Player: " + player + " with Reason: " + reason);
      
      Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
      if (chatPlayer != null) {
        chatPlayer.ban(reason, message.getSender(), cal);
        return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now banned");
      } else {
        // Player is offline. We will attempt to ban player only if this
        // player is in the database.
        DataBaseConnector.INSTANCE.banned(player, message.getSender(), true, cal, reason);

        Logger.log("Banning Player: " + player + " with Reason: " + reason, Logger.MOD);
        
        return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now banned");
      }
    }
  }
}
