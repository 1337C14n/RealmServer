package command.commands;

import main.DataBaseConnector;
import packets.CommandMessage;
import packets.PlayerMessage;
import command.Command;

public class Warn extends Command {

	public Warn(CommandMessage message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() {
    if(message.getArgs().length != 3){
      return new PlayerMessage(message.getSender(), "/warn <playerName>"); 
    } 
    
    String player = message.getArgs()[0];
    String reason = message.getArgs()[2];
    
    DataBaseConnector.INSTANCE.warn(message.getSender(), player, reason);
    
		return new PlayerMessage(message.getSender(), "&f[&2*&f] &7Player Warned");
	}
	
}
