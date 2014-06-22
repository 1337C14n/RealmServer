package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

import main.leetNetworkHandlers.ClientLoginPacketHandler;
import main.leetNetworkHandlers.CommandMessagePacketHandler;
import main.leetNetworkHandlers.HeartBeatHandler;
import main.leetNetworkHandlers.KickPacketHandler;
import main.leetNetworkHandlers.MiniGameStatusPacketHandler;
import main.leetNetworkHandlers.PermissionsPacketHandler;
import main.leetNetworkHandlers.PlayerLoginPacketHandler;
import main.leetNetworkHandlers.PlayerListPacketHandler;
import main.leetNetworkHandlers.PlayerProxyPacketHandler;
import main.leetNetworkHandlers.RequestServerPacketHandler;
import main.logging.Logger;
import minigame.MiniGame;
import minigame.MiniGameRegistry;
import packets.Broadcast;
import packets.ChannelList;
import packets.ChatMessage;
import packets.ClientLogin;
import packets.CommandMessage;
import packets.HeartBeat;
import packets.Kick;
import packets.MiniGameStatusPacket;
import packets.Packet;
import packets.Permissions;
import packets.PlayerList;
import packets.PlayerLogin;
import packets.PlayerProxyPacket;
import packets.RequestServer;
import chat.Announcement;
import chat.packetHandlers.BroadCastHandler;
import chat.packetHandlers.ChannelListHandler;
import chat.packetHandlers.ChatMessagePacketHandler;

import com.google.common.base.Throwables;

// Client Connection. Client Handler
public class Client implements Runnable {

  private final Socket client;
  private final int clientId;
  private String type;
  private ObjectOutputStream out = null;
  private ObjectInputStream in = null;

  private ClientWriter writer;
  private Announcement announce;
  
  public Thread writerThread;
  Thread announcementThread;
  
  @SuppressWarnings("serial")
  public static final HashMap<Class<? extends Packet>, Class<? extends PacketHandler>>
    packetHandlerMap = new HashMap<Class<? extends Packet>, Class<? extends PacketHandler>>() {
    {
      put(Broadcast.class, BroadCastHandler.class);
      put(ChannelList.class, ChannelListHandler.class);
      put(ChatMessage.class, ChatMessagePacketHandler.class);
      put(ClientLogin.class, ClientLoginPacketHandler.class);
      put(CommandMessage.class, CommandMessagePacketHandler.class);
      put(HeartBeat.class, HeartBeatHandler.class);
      put(Permissions.class, PermissionsPacketHandler.class);
      put(PlayerList.class, PlayerListPacketHandler.class);
      put(PlayerLogin.class, PlayerLoginPacketHandler.class);
      put(Kick.class, KickPacketHandler.class);
      put(PlayerProxyPacket.class, PlayerProxyPacketHandler.class);
      put(MiniGameStatusPacket.class, MiniGameStatusPacketHandler.class);
      put(RequestServer.class, RequestServerPacketHandler.class);
    }
  };


  public Client(Socket client, int clientId) throws IOException {
    this.client = client;
    this.clientId = clientId;
    createStreams();

    writer = new ClientWriter(out);
    writerThread = new Thread(writer);
    writerThread.start();
  }

  private void createStreams() throws IOException {
    out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
    out.flush();
    in = new ObjectInputStream(new BufferedInputStream((client.getInputStream())));
  }

  @Override
  public void run() {
    
    /*
     * On client connection 
     * Initial Handshake with client
     * We will initialize by sending a ClientLogin request to the client
     * The client will respond with its current status. Client type.
     */

    writer.addToQueue(new ClientLogin(null, null));

    while (true) {
      Object packet;
      try {
        packet = in.readObject();

        if (packet instanceof ClientLogin) {

          type = ((ClientLogin) packet).getType();

          Logger.log("Client Connected: " + type + " Name: " + ((ClientLogin) packet).getName(), Logger.INFO);
          
          //Theses are hard coded types
          //TODO change from hard coded types to dynamic 
          if(type.equals("chat")){
            //Clients is an external chat client.
            Clients.INSTANCE.chatClients.put(clientId, type);
          } else if (type.equals("hub")) {
            Clients.INSTANCE.setMaster(clientId);
          }
          
          //Register the client with the clients table
          Clients.INSTANCE.serverNames.put(((ClientLogin) packet).getName(), clientId);
          
          //Register mini game if it exists
          Logger.log("Mini Game Registered: " + type , Logger.DEBUG);
          MiniGameRegistry.INSTANCE.putMiniGame(this, new MiniGame(((ClientLogin) packet).getName(), type));

          break;
        } else if(packet instanceof HeartBeat){
          writer.addToQueue((Packet) packet);
        }
      } catch (ClassNotFoundException | IOException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      } 
    }
    
    /* Before the server starts will will ask the client if there are any
     * players that are already logged in.
     * Send a null player list as the request.
     */ 
    writer.addToQueue(new PlayerList(null));

    // Start Sending Announcements

    announce = new Announcement(this);

    announcementThread = new Thread(announce);
    announcementThread.start();

    while (true) {
      try {
        Object packet = in.readObject();
        
        try {
          PacketHandler handler = classForType((Packet) packet).getConstructor(Packet.class, Client.class).newInstance(packet, this);
          if(!(packet instanceof HeartBeat)){
            Logger.log("Incoming: " + handler.getClass().getSimpleName(), Logger.DEBUG);
          }
          if(handler instanceof AsycPacketHandler){
            Logger.log("Incoming: AsychronousPacketHandler", Logger.DEBUG);
            Thread handlerThread = new Thread((Runnable) handler);
            handlerThread.run();
          } else {
            writer.addToQueue(handler.handle());
          }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
            | InvocationTargetException | NoSuchMethodException | SecurityException e) {
          Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
        }
        
      } catch (ClassNotFoundException e) {
        Logger.log("Uknown Packet: Client ID: " + clientId + " " + e.getMessage(), Logger.WARNING);
      } catch (IOException e) {
        Logger.log("Session was forceably closed", Logger.INFO);
        
        if(e.getMessage() != null){
          Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
        }
        dispose();
        break;
      }
    }
  }
  
  public static Class<? extends PacketHandler> classForType(Packet packet) {
    return (Class<? extends PacketHandler>) (packetHandlerMap.containsKey(packet.getClass()) ? packetHandlerMap.get(packet.getClass()) : null);
  }

  public ClientWriter getWriter() {
    return writer;
  }

  private void dispose() {
    // Need to removal all references to this object
    for (Player player : PlayerHandler.INSTANCE.getChatPlayers()) {
      if (player.getActiveServer() == clientId) {
        PlayerHandler.INSTANCE.logOut(player.getName());
      }
    }
    
    //Mini game may be shutting down or restarting   
    MiniGameRegistry.INSTANCE.removeServer(this);

    announce.stop();

    Clients.INSTANCE.disposeClient(clientId);

    if (writer != null) {
      writer.stop();
    }

    try {
      out.close();
      in.close();
    } catch (IOException e1) {
    }

    if (client.isConnected()) {
      try {
        client.close();
      } catch (IOException e) {
        Logger.log(Throwables.getStackTraceAsString(e), Logger.CRITICAL);
      }
    }
    
    writer = null;
    announcementThread = null;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getClientId() {
    return clientId;
  }
  
  public void write(Packet packet){
    this.writer.addToQueue(packet);
  }

}
