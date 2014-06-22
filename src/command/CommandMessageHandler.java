package command;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.google.common.base.Throwables;

import main.logging.Logger;
import packets.CommandMessage;
import packets.Packet;
import command.commands.Ban;
import command.commands.Ch;
import command.commands.Ignore;
import command.commands.Ignored;
import command.commands.Join;
import command.commands.Msg;
import command.commands.Mute;
import command.commands.Perm;
import command.commands.Reply;
import command.commands.Unban;
import command.commands.Warn;

public class CommandMessageHandler {
  
  CommandMessage message;
  
  @SuppressWarnings("serial")
  public static final HashMap<String, Class<? extends Command>> commandTypes = new HashMap<String, Class<? extends Command>>() {
    {
      put("ban", Ban.class);
      put("ch", Ch.class);
      put("msg", Msg.class);
      put("mute", Mute.class);
      put("r", Reply.class);
      put("unban", Unban.class);
      put("warn", Warn.class);
      put("ignore", Ignore.class);
      put("join", Join.class);
      put("perm", Perm.class);
      put("ignored", Ignored.class);
    }
  };

  public CommandMessageHandler(CommandMessage message){
    this.message = message;
  }
  
  public Packet handle(){
    try {
      String messageCommand = message.getCommand().toLowerCase();
      
      String argsMessage = "";
      for(String arg : message.getArgs()){
        argsMessage += arg + " ";
      }
      Logger.log(message.getSender() + " issued command: " + messageCommand + " with " + argsMessage, Logger.INFO);
      Command command = classForType(messageCommand).getConstructor(CommandMessage.class).newInstance(message);
      Packet returnPacket = (Packet) command.execute();
      return returnPacket;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      return null;
    }
  }
  
  public static Class<? extends Command> classForType(String type) {
    return (Class<? extends Command>) (commandTypes.containsKey(type) ? commandTypes.get(type) : null);
  }

}
