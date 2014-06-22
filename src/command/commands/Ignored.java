package command.commands;

import main.PlayerHandler;
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
    
    for(String player: PlayerHandler.INSTANCE.getPlayerFromPlayerName(sender).getIgnoredPlayers()){
      ignoredPlayers += (player + " "); 
    }
    
    return new PlayerMessage(sender, "&7[&2*&7] Ignoring: &f" + ignoredPlayers);
  }

}
