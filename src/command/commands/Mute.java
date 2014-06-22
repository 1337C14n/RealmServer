package command.commands;

import java.util.Calendar;

import command.Command;
import main.Player;
import main.DataBaseConnector;
import main.PlayerHandler;
import chat.mute.Muted;
import packets.CommandMessage;
import packets.PlayerMessage;

public class Mute extends Command {

  public Mute(CommandMessage message) {
    super(message);
  }

  @Override
  public Object execute() {
    String playerName = message.getArgs()[0];
    String time = message.getArgs()[1];
    String reason = message.getArgs()[2];

    Player player = PlayerHandler.INSTANCE.getPlayerFromPlayerName(playerName);
    
    /*
     * Check to see if the player is offline or online.
     * If the player is not null they are online
     */
    if(player != null){
      //The player is online. Was there a time specified by the user?
      if(time != null){
        Calendar cal = Calendar.getInstance();      
        cal.add(Calendar.SECOND,Integer.parseInt(time));
        player.setUnmuteDate(cal);
        
        Muted.INSTANCE.addPlayer(playerName);
        
      } else {  
        Muted.INSTANCE.removePlayer(player.getName());
        DataBaseConnector.INSTANCE.savePlayer(player);
      } 
      player.setMuted(!player.isMuted());
    } else {
      boolean muted = DataBaseConnector.INSTANCE.isMuted(playerName);
      if(muted){
        DataBaseConnector.INSTANCE.muteOffline(playerName, null , false);
        DataBaseConnector.INSTANCE.muteLog(playerName, message.getSender(), reason);
        return new PlayerMessage(message.getSender(), "Unmuted Player"); 
        
      } else {
        if(time != null){
          Calendar cal = Calendar.getInstance();      
          cal.add(Calendar.SECOND,Integer.parseInt(time));
          DataBaseConnector.INSTANCE.muteOffline(playerName, cal , true);
          DataBaseConnector.INSTANCE.muteLog(playerName, message.getSender(), reason);
          return new PlayerMessage(message.getSender(), "Player Muted"); 
        } else {  
          DataBaseConnector.INSTANCE.muteOffline(playerName, null , true);
          DataBaseConnector.INSTANCE.muteLog(playerName, message.getSender(), reason);
          return new PlayerMessage(message.getSender(), "Unmuted Player"); 
        } 
      }
    }
    
    return null;
  }

}
