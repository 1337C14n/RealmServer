package command.commands;

import java.util.ArrayList;

import main.PlayerHandler;
import main.logging.Logger;
import command.Command;
import chat.ChannelHandler;
import chat.PrivateChannelMap;
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
      if(message.getArgs().length > 1){ 
        String messages = "";
        for(int i = 1; i < message.getArgs().length; i++){
          messages += message.getArgs()[i] + " ";
        }
        
        // Must be a response to an open channel.
        String openPrivateChannel = PrivateChannelMap.INSTANCE.checkForOpenChannel(message.getSender());
        Logger.log("Open Private Channel: " + openPrivateChannel, Logger.DEBUG);
        
        if(openPrivateChannel != null){
          // /msg <player> <message> When there is an open channel
          
          //we are going to allow them to respond to this type of message
          
          ChannelHandler.INSTANCE.getChannelFromChannelName(openPrivateChannel).SendMessage(messages, null, message.getSender(), false);
        } else {
          // /msg <player> <message> When there isnt an open channel
          String sender = message.getSender();
          
          ChannelHandler.INSTANCE.newChannel("_private_" + sender, "", sender);
          
          ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(sender, "_private_" + sender);
          
          ChannelHandler.INSTANCE.addPlayerToChannel(playerToConnectTo, "_private_" + sender);
          
          ChannelHandler.INSTANCE.getChannelFromChannelName("_private_" + sender).SendMessage(messages, null, sender, false);
        }
      } else { // /msg <player>
        String sender = message.getSender();
        
        ChannelHandler.INSTANCE.newChannel("_private_" + sender, "", sender);
        
        ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(sender, "_private_" + sender);
        
        ChannelHandler.INSTANCE.addPlayerToChannel(playerToConnectTo, "_private_" + sender);
        
        return new PlayerMessage(message.getSender(), "&7[&2*&7] Opened a channel with &b" + playerToConnectTo);        
      }
    } else {
      return new PlayerMessage(message.getSender(), "&7[&4*&7] Player not online");
    }
    return null;
  }
    
}
