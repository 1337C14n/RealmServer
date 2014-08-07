package command.commands;

import packets.CommandMessage;
import packets.PlayerMessage;
import command.Command;

public class Ignored extends Command{

  public Ignored(CommandMessage message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object execute() {
    String ignoredPlayers = "";
    
    for(String player: sender.getIgnoredPlayers()){
      ignoredPlayers += (player + " "); 
    }
    
    return new PlayerMessage(sender.getName(), "&7[&2*&7] Ignoring: &f" + ignoredPlayers);
  }

}
