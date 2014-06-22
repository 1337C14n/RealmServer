package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import main.logging.Logger;

import com.google.common.base.Throwables;

import configuration.Config;

public class Realmd {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    int clientNumber = 0;
    ServerSocket server = null;
    Config.INSTANCE.populate(); // This is the first thing we need to do.
    
    @SuppressWarnings("unused")
    Logger logger = new Logger();
    
    Logger.log("Starting Realm Server", Logger.DEBUG);
    
    while(true){
      try {
        server = new ServerSocket(2000);
        

        Logger.log("Getting database information", Logger.DEBUG);
        DataBaseConnector.INSTANCE.populate();

        Logger.log("Realm Server Started", Logger.DEBUG);
  
        while (true) {
          Socket client = server.accept();
          Client connectedClient = new Client(client, clientNumber);
          Thread clientThread = new Thread(connectedClient);
          clientThread.start();
          Clients.INSTANCE.clients.put(clientNumber, connectedClient);
          Logger.log("Server Logged in: " + clientNumber, Logger.INFO);
          
          clientNumber++;
        }
  
      } catch (IOException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
        if(server != null){
          server.close();
        }
        server = null;
        break;
      }
    }
  }
}
