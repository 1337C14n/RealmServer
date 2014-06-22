package chat;

import java.util.ArrayList;

import com.google.common.base.Throwables;

import configuration.Config;
import packets.Broadcast;
import main.Client;
import main.logging.Logger;

public class Announcement implements Runnable {

  Client handler;
  private volatile boolean running = true;
  private int interval;
  private ArrayList<String> announcables;

  public Announcement(Client handler) {
    this.handler = handler;
    this.announcables = Config.INSTANCE.getConfig().getAnnouncables();
    this.interval = Config.INSTANCE.getConfig().getInterval();
  }

  @Override
  public void run() {
    
    //Spawned per connection

    int message = 0;

    while (running) {
      if (message > announcables.size() - 1) {
        message = 0;
      }

      handler.getWriter().addToQueue(new Broadcast(announcables.get(message)));

      try {
        Thread.sleep(interval);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }

      message++;
    }
  }

  public void stop() {
    running = false;
  }

}
