package chat;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import main.Player;
import main.DataBaseConnector;
import main.PlayerHandler;
import main.logging.Logger;

public enum ChannelHandler {
  INSTANCE;

  private ConcurrentHashMap<String, Channel> channelList = new ConcurrentHashMap<String, Channel>();

  public Channel getChannelFromChannelName(String channelName) {
    Channel channel = channelList.get(channelName);
    if (channel == null) {
      //Player in channel that doesn't exist.
      return channelList.get("g");
    }
    return channel;
  }

  public void newChannel(String channelName, String prefix, CopyOnWriteArrayList<String> players) {
    Channel channel = new Channel(channelName.toLowerCase(), channelName, prefix, players, true);
    channelList.put(channelName, channel);
  }
  
  public void newChannel(String channelName, String prefix, String password, CopyOnWriteArrayList<String> players) {
	    Channel channel = new Channel(channelName.toLowerCase(), channelName, prefix, password, players, true);
	    channelList.put(channelName, channel);
	  }

  public void addChannel(Channel channel) {
    channelList.put(channel.getChannelName(), channel);
  }

  public void newChannel(String channelName, String prefix, String creatorName) {
    CopyOnWriteArrayList<String> players = new CopyOnWriteArrayList<String>();
    players.add(creatorName);
    Channel channel = new Channel(channelName.toLowerCase(), channelName, prefix, players, true);
    if(channel.getName().contains("_private_")){
      channel.setPrivate(true);
    }
    Logger.log("Adding new Channel: " + channelName + ". is Private: " + channel.isPrivate(), Logger.INFO);
    channelList.put(channelName, channel);
  }
  
  public void newChannel(String channelName, String prefix, String password, String creatorName) {
	    CopyOnWriteArrayList<String> players = new CopyOnWriteArrayList<String>();
	    players.add(creatorName);
	    Channel channel = new Channel(channelName.toLowerCase(), channelName, prefix, password, players, true);
	    if(channel.getName().contains("_private_")){
	      channel.setPrivate(true);
	    }
	    Logger.log("Adding new Channel: " + channelName + ". is Private: " + channel.isPrivate(), Logger.INFO);
	    channelList.put(channelName, channel);
	  }

  public boolean ChannelExists(String channel) {
    return channelList.containsKey(channel);
  }

  public void RemovePlayer(String player) {
    Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
    CopyOnWriteArrayList<String> channels = chatPlayer.getChannels();

    for (String channel : channels) {
      ChannelHandler.INSTANCE.getChannelFromChannelName(channel).removePlayer(player);
    }
  }

  public void addPlayerToChannelSetDefault(String player, String channel) {
    if(channelList.containsKey(channel)){
      Channel channelChannel = channelList.get(channel);
      
      if(channelChannel.isPrivate()){
        
        String owner = channelChannel.getOwners().get(0);
        
        for(String channelPlayer : channelChannel.getOwners()){
          
          if(!channelPlayer.equalsIgnoreCase(owner)){
            
            channelChannel.removePlayer(channelPlayer);
            PlayerHandler.INSTANCE.getPlayerFromPlayerName(channelPlayer).removeChannel(channelChannel.getChannelName());
          }
        }    
      }
      
      if(!channelChannel.ContainsPlayer(player)){
        getChannelFromChannelName(channel).addPlayer(player);
      }

      PlayerHandler.INSTANCE.getPlayerFromPlayerName(player).setActiveChannel(channel);  
      
    } else {
      Logger.log("Channel Did Not Exist: Could not add player to channel and set default", Logger.WARNING);
      Logger.log("Player: " + player + " Channel: " + channel, Logger.WARNING);
    }
  }

  public void addPlayerToChannel(String player, String channel) {
    if (!channelList.get(channel).ContainsPlayer(player)) {
      getChannelFromChannelName(channel).addPlayer(player);
    }
  }

  public void removePlayerFromChannel(String player, String channelToBeRemovedFrom) {
    Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
    CopyOnWriteArrayList<String> channels = chatPlayer.getChannels();

    for (String channel : channels) {
      if (channel == channelToBeRemovedFrom) {
        ChannelHandler.INSTANCE.getChannelFromChannelName(channel).removePlayer(player);
        return;
      }
    }
  }

  public String getChannelList() {
    String channels = "";
    channels += "List of Channels:";
    for (Channel ch : channelList.values()) {
      if(!ch.isPrivate()){
        channels += ("\n&b" + ch.getChannelName() + "&f");
      }
    }
    return channels;
  }
  
  public ArrayList<String> getChannelListArray(){
    ArrayList<String> channels = new ArrayList<String>();
    
    for(String channelName : channelList.keySet()){
      channels.add(channelName);
    }
    
    return channels;
  }

  public void removeChannel(String name) {
    if (channelList.containsKey(name)) {
      Channel channel = channelList.get(name);
      
      DataBaseConnector.INSTANCE.removeChannel(name);

      for (String player : channel.getPlayers()) {
        Player chatPlayer = PlayerHandler.INSTANCE.getPlayerFromPlayerName(player);
        chatPlayer.removeChannel(name);
      }
      channelList.remove(name);
    }
  }

  public void activateChannels(Player player) {

  }
}
