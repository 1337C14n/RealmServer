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
    
    if (time == null) {

      Logger.log("Banning Player: " + player + " with Reason: " + reason, Logger.MOD);
      
      Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
      if (chatPlayer != null) {
        chatPlayer.ban(message.getSender(), reason);
        PlayerHandler.INSTANCE.addPlayerToBanCache(player);
      } else {
        // Player is offline. We will attempt to ban player only if this
        // player is in the database.
        if (DataBaseConnector.INSTANCE.isBanned(player)) {
          DataBaseConnector.INSTANCE.banned(player, message.getSender(), false, null, reason);
          PlayerHandler.INSTANCE.removePlayerFromBanCache(player);
          Logger.log("un banning Player: " + player + " with Reason: " + reason, Logger.MOD);
          
          return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now unbanned");
        } else {
          DataBaseConnector.INSTANCE.banned(player, message.getSender(), true, null, reason);
          PlayerHandler.INSTANCE.addPlayerToBanCache(player);
          Logger.log("Banning Player: " + player + " with Reason: " + reason, Logger.MOD);
          
          return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now banned");
        }
      }
    } else {

      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.SECOND, Integer.parseInt(time));

      Logger.log("Banning Player: " + player + " with Reason: " + reason, Logger.MOD);
      Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
      if (chatPlayer != null) {
        chatPlayer.ban(reason, message.getSender(), cal);
      } else {
        // Player is offline. We will attempt to ban player only if this
        // player is in the database.
        if (DataBaseConnector.INSTANCE.isBanned(player)) {
          DataBaseConnector.INSTANCE.banned(player, message.getSender(), false, cal, reason);
          PlayerHandler.INSTANCE.removePlayerFromBanCache(player);
          Logger.log("unBanning Player: " + player + " with Reason: " + reason, Logger.MOD);
          
          return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now unbanned");
        } else {
          DataBaseConnector.INSTANCE.banned(player, message.getSender(), true, cal, reason);
          PlayerHandler.INSTANCE.addPlayerToBanCache(player);
          Logger.log("Banning Player: " + player + " with Reason: " + reason, Logger.MOD);
          
          return new PlayerMessage(message.getSender(), "&7[&2*&7] " + player + " is now banned");
        }
      }
    }

    return null;
  }

}
