package command.commands;

import packets.CommandMessage;
import packets.PlayerMessage;
import command.Command;

public class Transfer extends Command{
  
  /*
   * Transfer commands sends the a target player to
   * the target server
   */
  public Transfer(CommandMessage message) {
    super(message);
  }

  @Override
  public Object execute() {
    if(argsLength == 2){
      //String playerName = args[0];
      //String serverName = args[1];
    }
    
    return new PlayerMessage(sender.getName(), "&7[&4*&7] Invalid. Please provide /tran <playername> <server>");
  }
  

}
