package chat.mute;

import java.util.Calendar;

import com.google.common.base.Throwables;

import main.PlayerHandler;
import main.logging.Logger;

public class MuteThread implements Runnable {
  
  Calendar date;
  String playerName;
  boolean cancel = false;
  
  public MuteThread(String player){
    date = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player).getUnmuteDate();
    this.playerName = player;
  }
  
  @Override
  public void run() {
    while(true){
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
      
      if(cancel){
        break;
      }
      
      Calendar cal = Calendar.getInstance();
      
      if(cal.compareTo(date) > 0){
        PlayerHandler.INSTANCE.getPlayerFromPlayerName(playerName).setMuted(false);
        PlayerHandler.INSTANCE.getPlayerFromPlayerName(playerName).setUnmuteDate(null);
        break;
      }
    }    
  }
  
  public void cancel(){
    cancel = true;
  }
}
