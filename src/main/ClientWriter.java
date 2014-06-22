package main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import packets.HeartBeat;
import packets.Packet;

import com.google.common.base.Throwables;

import main.logging.Logger;

public class ClientWriter implements Runnable {
  ObjectOutputStream out = null;
  
  private volatile boolean running = true;
  
  private ConcurrentLinkedQueue<Packet> outPut = new ConcurrentLinkedQueue<Packet>();
  
  public ClientWriter(ObjectOutputStream out){
    this.out = out;
  }
  
  public void addToQueue(Packet e){
    if(e != null){
      outPut.add(e);
    }
  }

  @Override
  public void run() {
    while(running){
      while(!outPut.isEmpty()){
        try {
          Packet packet = outPut.poll();
          if(!(packet instanceof HeartBeat)){
            Logger.log("Out Going: " + packet.getClass().getSimpleName(), Logger.DEBUG);
          }
          out.writeObject(packet);
          out.flush();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
        }
      }
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block  
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
  }
  
  public void stop(){
    running = false;
  }
  
}
