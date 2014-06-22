package command.commands;

import java.util.ArrayList;

import main.Clients;
import main.Player;
import main.PlayerHandler;
import main.logging.Logger;
import packets.CommandMessage;
import packets.PlayerMessage;
import command.Command;

public class Ignore extends Command{

  /**
   * Command to ignore or unignore another player
   * @param message
   */
  public Ignore(CommandMessage message) {
    super(message);
  }

  @Override
  public Object execute() {
    Logger.log("Executing Ignore Command" , Logger.DEBUG);
    if(message.getArgs().length == 1){
      String player = message.getArgs()[0];
      ArrayList<String> closestPlayers = PlayerHandler.INSTANCE.getClosestPlayers(player);
      String closestPlayer = "";
      
      if(player == null || closestPlayers.size() < 1){
        return new PlayerMessage(message.getSender(), "&7[&4*&7] Player doesn't exist");
      } else if (closestPlayers.size() == 1){
        closestPlayer = closestPlayers.get(0);
      } else if (closestPlayers.size() > 1){
        closestPlayer = closestPlayers.get(0);
        String playersString = "";
        for(String tempPlayer : closestPlayers){
          playersString += tempPlayer + ", ";
        }
        Clients.INSTANCE.getClientFromInt(PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()).getActiveServer())
          .getWriter().addToQueue(new PlayerMessage(message.getSender(), playersString));
      }
      
      Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender());
      Logger.log(message.getSender() + " is ignoring " + closestPlayer + " " + chatPlayer.isIgnoring(closestPlayer), Logger.DEBUG );
      
      if(!chatPlayer.isIgnoring(closestPlayer)){

        chatPlayer.addIgnoredPlayer(closestPlayer);

        Logger.log("ignored", Logger.DEBUG );
        return new PlayerMessage(message.getSender(), "&7[&2*&7] Ignored " + closestPlayer);
        
      }
      
      chatPlayer.removeIgnoredPlayer(closestPlayer);

      Logger.log("unignored", Logger.DEBUG );
      return new PlayerMessage(message.getSender(), "&7[&2*&7] Unignored " + closestPlayer);
      
    } else {
      return new PlayerMessage(message.getSender(), "&7[&4*&7] Please specify a player");
    }
  }

}
