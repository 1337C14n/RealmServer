package main;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import chat.ChannelHandler;
import main.logging.Logger;

public enum PlayerHandler {
  INSTANCE;

  private ConcurrentHashMap<String, Player> playerList = new ConcurrentHashMap<String, Player>();
  private ConcurrentHashMap<String, Boolean> banCache = new ConcurrentHashMap<String, Boolean>();

  public Player getPlayerFromPlayerName(String player) {
    if (!playerList.containsKey(player)) {
      // Player does not exists
      return null;
    }
    return playerList.get(player);
  }

  public void newPlayer(String playerName, String activeChannel) {
    Player newPlayer = new Player(playerName, activeChannel);
    playerList.put(playerName, newPlayer);
  }

  public void newPlayer(Player player) {
    playerList.put(player.getName(), player);
  }

  public void playerLogin(String name, int clientId) {
    if (!DataBaseConnector.INSTANCE.populatePlayer(name)) {

      Logger.log("New Player: " + name, Logger.INFO);
      // Player Doesn't Exists in database Create a new Player

      // TODO Change from statically defined variable
      CopyOnWriteArrayList<String> channels = new CopyOnWriteArrayList<String>();
      channels.add("g");
      Player player = new Player(name, "g", channels);
      DataBaseConnector.INSTANCE.savePlayer(player);
      newPlayer(player);

    }
    Player player = getPlayerFromPlayerName(name);

    // Player could be null if they were kicked.
    if (player != null) {
      player.setActiveServer(clientId);

      CopyOnWriteArrayList<String> channels = player.getChannels();

      for (String channel : channels) {
        ChannelHandler.INSTANCE.addPlayerToChannel(player.getName(), channel);
      }
    }
  }

  public void logOut(String name) {
    if (!PlayerExists(name)) {
      Logger.log("Player did not exist can not log out", Logger.WARNING);
      return;
    }

    for (String channelName : (PlayerHandler.INSTANCE.getPlayerFromPlayerName(name)).getChannels()) {
      ChannelHandler.INSTANCE.removePlayerFromChannel(name, channelName);
    }

    PlayerHandler.INSTANCE.getPlayerFromPlayerName(name).setLastPMFrom(null);

    DataBaseConnector.INSTANCE.savePlayer(PlayerHandler.INSTANCE.getPlayerFromPlayerName(name));

    removePlayer(name);
  }

  public ArrayList<Player> getChatPlayers() {
    ArrayList<Player> players = new ArrayList<Player>();

    Set<String> keys = playerList.keySet();

    for (String key : keys) {
      players.add(playerList.get(key));
    }
    return players;
  }

  public ArrayList<String> getPlayerList() {
    ArrayList<String> players = new ArrayList<String>();

    Set<String> keys = playerList.keySet();

    for (String key : keys) {
      players.add(playerList.get(key).getName());
    }
    return players;
  }

  public void addPlayer(Player player) {
    playerList.put(player.getName(), player);
  }

  public boolean PlayerExists(String name) {
    return name == null ? false : playerList.containsKey(name);
  }

  public void addChannelAndSetDefault(String name, String channelName) {
    Player player = getPlayerFromPlayerName(name);

    player.setActiveChannel(channelName);
    player.addToChannel(channelName);

  }

  public void removePlayer(String player) {
    if (playerList.containsKey(player)) {
      playerList.remove(player);
    }
  }

  public String getPlayerChannelList(String name) {
    Player player = getPlayerFromPlayerName(name);

    String list = player.getChannels().toString();

    return list;
  }

  public ArrayList<String> getClosestPlayers(String name) {
    Set<String> players = playerList.keySet();

    ArrayList<String> possiblePlayers = new ArrayList<String>();

    for (String player : players) {
      if (player.toLowerCase().contains(name.toLowerCase())) {
        possiblePlayers.add(player);
      }
    }

    return possiblePlayers;
  }

  public void addPlayerToBanCache(String player) {
    banCache.put(player, true);
  }

  public boolean banCacheContains(String player) {
    return banCache.containsKey(player);
  }

  public void removePlayerFromBanCache(String player) {
    if (banCache.containsKey(player)) {
      banCache.remove(player);
    }
  }
}