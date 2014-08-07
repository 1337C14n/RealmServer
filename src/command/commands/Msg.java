package command.commands;

import java.util.ArrayList;

import main.Player;
import main.PlayerHandler;
import main.logging.Logger;
import command.Command;
import chat.ChannelHandler;
import packets.CommandMessage;
import packets.PlayerMessage;
/*
 * ./msg
 * ./msg <name>
 * ./msg <name> <message>
 */
public class Msg extends Command {

  public Msg(CommandMessage message) {
    super(message);
  }

  @Override
  public Object execute() {
    
    if(message.getArgs().length < 1){
      return new PlayerMessage(sender.getName(), "&7[&4*&7] /msg <name> <message>");
    }
    
    String playerToConnectTo = message.getArgs()[0];   
    String fullPlayerName = null;
    
    if(PlayerHandler.INSTANCE.getPlayerFromPlayerName(playerToConnectTo) != null){
      fullPlayerName = playerToConnectTo;
    } else {
      ArrayList<String> players = PlayerHandler.INSTANCE.getClosestPlayers(playerToConnectTo);
      
      if(players.size() == 1){
        fullPlayerName = players.get(0);
      } else if (players.size() > 1){
        fullPlayerName = players.get(0);
        String playersString = "";
        
        for(String player : players){
          playersString += player + ", ";
        }
        
        sender.getClient().getWriter().addToQueue(new PlayerMessage(message.getSender(), playersString));
      }
    }
    
    playerToConnectTo = fullPlayerName;
    
    if(PlayerHandler.INSTANCE.PlayerExists(playerToConnectTo)){
      
      Player recievingPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(playerToConnectTo);
      
      if(message.getArgs().length > 1){ 
        /*
         * ./msg <player> <message>
         */
        
        //The message consists of multiple arguments from 1 to length of args
        String messages = "";
        
        for(int i = 1; i < message.getArgs().length; i++){
          messages += message.getArgs()[i] + " ";
        }
        
        //Set the player we are sending a private message to.
        
        recievingPlayer.setLastPMFrom(sender.getName());
        
        recievingPlayer.sendPrivateMessage(recievingPlayer, messages);
        

      } else { // /msg <player>
        this.sender.setPlayerTalkingTo(recievingPlayer.getName());
        this.sender.setInPrivateChat(true);
        recievingPlayer.setLastPMFrom(sender.getName());
      }
    } else {
      return new PlayerMessage(message.getSender(), "&7[&4*&7] Player not online");
    }
    return null;
  }
    
}
