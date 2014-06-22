package command.commands;

import main.Clients;
import main.DataBaseConnector;
import main.PlayerHandler;
import packets.CommandMessage;
import packets.PermissionChange;
import command.Command;

public class Perm extends Command{
  /*
   * ./perm player <player> group add <GroupName> //adds a player to a group
   * ./perm player <player> group remove <GroupName> //removes a player from a group
   * ./perm player <player>  //lists all player permissions
   * 
   * ./perm player <player> add <permission>
   * ./perm player <player> remove <permission>
   * ./perm player <player> add <permission> server <server>
   * ./perm player <player> remove <permission> server <server>
   * 
   * ./perm promote <player>  //promote player
   * ./perm demote <player>  //demote player
   * 
   * ./perm group add <permission>  //adds a permission to a group on current server
   * ./perm group remove <permission>  //removes a permission from a group on current server
   * ./perm group add <permission> server <server> //adds a permission on specified server
   * ./perm group remove <permission> server <server> //removes a permission on specified server
   */
  
  public Perm(CommandMessage message) {
    super(message);
  }

  @Override 
  public Object execute() {   
    if(argsLength < 2){
      return response("&7[&4*&7] Invalid Command");
    }
    
    switch(args[0].toUpperCase()){
    case "PROMOTE":
      if(DataBaseConnector.INSTANCE.promotePlayer(args[1])){
        int currentServer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(args[1]).getActiveServer();
        

        Clients.INSTANCE.getClientFromInt(currentServer).getWriter().addToQueue(new PermissionChange(args[1]));
        
        return response("&7[&2*&7] Player Promoted");
      } else {
        return response("&7[&4*&7] Player did not exist");
      }
      //break;
      
    case "DEMOTE":
      if(DataBaseConnector.INSTANCE.demotePlayer(args[1])){
        int currentServer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(args[1]).getActiveServer();
        

        Clients.INSTANCE.getClientFromInt(currentServer).getWriter().addToQueue(new PermissionChange(args[1]));
        
        return response("&7[&2*&7] Player Promoted");
      } else {
        return response("&7[&4*&7] Player did not exist");
      }
    }
    
    return response("&7[&4*&7] Invalid Command");
  }

}
