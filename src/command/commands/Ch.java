package command.commands;

import command.Command;
import command.commands.Password;
import main.DataBaseConnector;
import main.PlayerHandler;
import main.logging.Logger;
import chat.Channel;
import chat.ChannelHandler;
import packets.CommandMessage;
import packets.PlayerMessage;

public class Ch extends Command {

  public Ch(CommandMessage message) {
    super(message);
  }

  @Override
  public Object execute() {

    for (int i = 0; i < message.getArgs().length; i++) {
      Logger.log(message.getArgs()[i], Logger.DEBUG);
    }

    if (message.getArgs().length <= 0) {
      return new PlayerMessage(message.getSender(), "&7[&4*&7] &7What did you want to do?");
    }

    String firstArg = message.getArgs()[0].toLowerCase();
    
    switch(firstArg){   
    case "list":
      return new PlayerMessage(message.getSender(), ChannelHandler.INSTANCE.getChannelList());
      
    case "active":
      return new PlayerMessage(message.getSender(), PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()).getActiveChannel());
      
    case "create": {
        String channelName = message.getArgs()[1].toLowerCase();
        if (!ChannelHandler.INSTANCE.ChannelExists(channelName)) {
          if (message.getArgs().length > 2) {
            if (message.getArgs()[2] != null && !message.getArgs()[2].isEmpty()) {
              String password = Password.hash(message.getArgs()[2]);
              ChannelHandler.INSTANCE.newChannel(channelName, channelName, password, message.getSender());
              ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(message.getSender(), channelName);
              PlayerHandler.INSTANCE.addChannelAndSetDefault(message.getSender(), channelName);
              DataBaseConnector.INSTANCE.saveChannel(ChannelHandler.INSTANCE.getChannelFromChannelName(channelName));
              return new PlayerMessage(message.getSender(), "&7[&2*&7] &7Channel &b" + channelName + " &7created and joined.");
            }
          } else {
            ChannelHandler.INSTANCE.newChannel(channelName, channelName, message.getSender());
            ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(message.getSender(), channelName);
            PlayerHandler.INSTANCE.addChannelAndSetDefault(message.getSender(), channelName);
            DataBaseConnector.INSTANCE.saveChannel(ChannelHandler.INSTANCE.getChannelFromChannelName(channelName));
            return new PlayerMessage(message.getSender(), "&7[&2*&7] &7Channel &b" + channelName + " &7created and joined.");
          }
        }
        
        return new PlayerMessage(message.getSender(), "&7[&4*&7] &7Channel already exists");
      }
      
    case "delete": {
        String channelName = message.getArgs()[1].toLowerCase();
        if (ChannelHandler.INSTANCE.ChannelExists(channelName)) {
          Channel channel = ChannelHandler.INSTANCE.getChannelFromChannelName(channelName);
  
          if (channel.getOwners().contains(message.getSender())) {
            for (int i = 0; i < channel.getPlayers().size(); i++) {
              Logger.log("Player " + channel.getPlayers().get(i) + " removed from channel " + channel, Logger.DEBUG);
              channel.removePlayer(channel.getPlayers().get(i));
            }
            ChannelHandler.INSTANCE.removeChannel(channelName);
            DataBaseConnector.INSTANCE.removeChannel(channelName);
            return new PlayerMessage(message.getSender(), "&7[&2*&7] Channel deleted");
          } else {
            return new PlayerMessage(message.getSender(), "&7[&4*&7] You are not the owner");
          }
        }
        
        return new PlayerMessage(message.getSender(), "&7[&4*&7] Channel does not exist");
      }

    case "leave": {
        if (PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()).getChannels().size() == 1) {
          return new PlayerMessage(message.getSender(), "&7[&4*&7] Must be active on at least one channel");
        }
        if (message.getArgs().length == 2) {
          Channel channel = ChannelHandler.INSTANCE.getChannelFromChannelName(message.getArgs()[1].toLowerCase());
          channel.removePlayer(message.getSender());
          PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()).removeChannel(channel.getChannelName());
          
          return new PlayerMessage(message.getSender(), "&7[&2*&7] left channel: &b" + channel.getName());
        }
        Channel channel = ChannelHandler.INSTANCE.getChannelFromChannelName(PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()).getActiveChannel());
        channel.removePlayer(message.getSender());
        PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()).removeChannel(channel.getChannelName());
        
        return new PlayerMessage(message.getSender(), "&7[&2*&7] left channel: &b" + channel.getName());
      }     
    
    case "join": {
        /*
         * if statement having a player join a channel, and if the channel has a
         * password, have them enter one in before joining.
         */
        if (message.getArgs().length > 2) {
          String channelName = message.getArgs()[1];
          Channel channel = ChannelHandler.INSTANCE.getChannelFromChannelName(channelName);
          if (ChannelHandler.INSTANCE.ChannelExists(channelName)) {
            if (channel.getPassword() != null && !channel.getPassword().isEmpty()) {
              String password = Password.hash(message.getArgs()[2]);
              if (channel.getPassword().equals(password)) {
                ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(message.getSender(), channelName);
                PlayerHandler.INSTANCE.addChannelAndSetDefault(message.getSender(), channelName);
                return new PlayerMessage(message.getSender(), "&7[&2*&7] joined channel: &b" + channelName);
              } else
                return new PlayerMessage(message.getSender(), "&7[&4*&7] Password is incorrect.");
            }
          } else {
            return new PlayerMessage(message.getSender(), "&7[&4*&7] Channel does not exist.");
          }
        }
        return new PlayerMessage(message.getSender(), "&7[&4*&7] /ch join <channelName> <optional password>.");
      }
    
    default: {       
        if (ChannelHandler.INSTANCE.ChannelExists(firstArg)) {
          Channel channel = ChannelHandler.INSTANCE.getChannelFromChannelName(firstArg);
          
          //Sender went to a channel they are no longer in a private channel
          sender.setInPrivateChat(false);
          
          /*
           * If there is no password for the channel just add them to it
           */
          if(channel.getPassword() == null){
            ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(message.getSender(), firstArg);
            PlayerHandler.INSTANCE.addChannelAndSetDefault(message.getSender(), firstArg);
            return new PlayerMessage(message.getSender(), "&7[&2*&7] joined channel: &b" + firstArg); 
          }
          
          /*
           * the channel has a password.
           * if that channel contains the player. Set as active channel
           * else that player must join the channel and add the password.
           */
          if(channel.ContainsPlayer(message.getSender())){
            ChannelHandler.INSTANCE.addPlayerToChannelSetDefault(message.getSender(), firstArg);
            PlayerHandler.INSTANCE.addChannelAndSetDefault(message.getSender(), firstArg);
            
            return new PlayerMessage(message.getSender(), "&7[&2*&7] joined channel: &b" + firstArg);
          }
          
          return new PlayerMessage(message.getSender(), "&7[&4*&7] You must enter a password /ch join <channel> <password>");
          
        } else {
          
          return new PlayerMessage(message.getSender(), "&7[&4*&7] Channel does not exist.");
        }
      }      
    }
  }
}
