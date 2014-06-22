package command.commands;

import main.PlayerHandler;
import minigame.MiniGameRegistry;
import packets.CommandMessage;
import packets.PlayerMessage;
import packets.RedirectPacket;
import command.Command;

public class Join extends Command{

  public Join(CommandMessage message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object execute() {
    String[] args = message.getArgs();
    String sender = message.getSender();
    
    if(args.length == 0){
      return new PlayerMessage(sender, "&7[&4*&7] Please try again");
    }
    
    String subCommand = args[0];
    
    if(subCommand.equalsIgnoreCase("sg")){
      /*
       * User is looking for Survival games For now we will put them in the first sg game that is open
       * If we cannot find one then we will just tell them that there are no games available
       * if the next word is list then we will send all open sg games and their status.
       */
      if(args.length > 1){
        String list = args[1];
        if(list.equalsIgnoreCase("list")){
          return new PlayerMessage(sender, MiniGameRegistry.INSTANCE.getListOfType("survivalgames"));
        }
      }
      
      //else we try to add them to a server
      
      String serverName = MiniGameRegistry.INSTANCE.getOpenServerOfType("survivalgames");
      
      if(serverName != null){
        //Proxying from one server to another
        PlayerHandler.INSTANCE.getPlayerFromPlayerName(sender).setProxying(true);
        
        //Lets connect them to that server
        return new RedirectPacket(sender, serverName);
      }
    }
    return new PlayerMessage(sender, "&7[&4*&7] No Open Games");
  }

}
