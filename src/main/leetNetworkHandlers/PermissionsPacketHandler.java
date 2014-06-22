package main.leetNetworkHandlers;

import packets.Packet;
import packets.Permissions;
import main.AsycPacketHandler;
import main.Client;
import main.Clients;

public class PermissionsPacketHandler extends AsycPacketHandler{

  public PermissionsPacketHandler(Packet packet, Client client) {
    super(packet, client);
  }

  @Override
  public Packet handle() {
    /*
     * NEW SYSTEM!
     * 
    String[] permissions = ((Permissions) packet).getPermissions();
    String playerName = ((Permissions) packet).getPlayerName();
    String server = ((Permissions) packet).getWorld(); //TODO world not server

    if (permissions == null) {
      Logger.log("Request for player permissions", Logger.DEBUG);
    }
    
    String[] perms = DataBaseConnector.INSTANCE.getPlayerPermissions(playerName, server);
    
    String prefix = DataBaseConnector.INSTANCE.getPlayerPrefix(playerName);
    
    ((Permissions) packet).setPermissions(perms);
    ((Permissions) packet).setPrefix(prefix);
    
    return packet;*/
    
    /*
     * OLD SYSTEM
     */
    
    String[] permissions = ((Permissions) packet).getPermissions();

    if (permissions == null) {
      // we need to get players permissions from the master server
      ((Permissions) packet).setOriginatingServer(client.getClientId());
      Clients.INSTANCE.getClientFromInt(Clients.INSTANCE.getMaster()).getWriter()
          .addToQueue(packet);
    } else {
      @SuppressWarnings("unused")
      String perms = "";

      for (String perm : ((Permissions) packet).getPermissions()) {
        perms += perm + "\n";
      }
      
      Clients.INSTANCE.getClientFromInt(((Permissions) packet).getOriginatingServer())
          .getWriter().addToQueue(packet);
    }
    return null;
    
  }

}
