package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import packets.Kick;

public class Player {

  /**
   * @param args
   */
  private String name;
  private int activeServer;

  private CopyOnWriteArrayList<String> channels;
  private HashMap<String, Boolean> ignoredPlayers;

  private String activeChannel;
  private boolean muted;
  Calendar unmuteDate;

  private boolean talkingInPM;
  private String lastPMFrom;

  String[] permissions;

  private volatile boolean isProxying = false;

  public Player(String playerName, String activeChannel, CopyOnWriteArrayList<String> channels) {
    this.name = playerName;
    this.channels = channels;
    this.activeChannel = activeChannel;
    this.muted = false;
    ignoredPlayers = new HashMap<String, Boolean>();
    this.lastPMFrom = null;
    this.talkingInPM = false;
  }

  public Player(String name, String activeChannel) {
    this.name = name;
    this.activeChannel = activeChannel;
    CopyOnWriteArrayList<String> defaultChannel = new CopyOnWriteArrayList<String>();
    ignoredPlayers = new HashMap<String, Boolean>();
    this.channels = defaultChannel;
    this.muted = false;
    this.lastPMFrom = null;
    this.talkingInPM = false;
  }

  public void addToChannel(String channel) {
    if (!channels.contains(channel)) {
      channels.add(channel);
    }
  }

  public void kick(String message) {
    Clients.INSTANCE.getClientFromInt(activeServer).getWriter().addToQueue(new Kick(name, message));
  }

  public void ban(String banee, String reason) {
    DataBaseConnector.INSTANCE.banned(name, banee, true, null, reason);
    kick(reason);
  }

  public void ban(String reason, String banee, Calendar cal) {
    DataBaseConnector.INSTANCE.banned(name, banee, true, cal, reason);
    kick(reason);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CopyOnWriteArrayList<String> getChannels() {
    return channels;
  }

  public void setChannels(CopyOnWriteArrayList<String> channels) {
    this.channels = channels;
  }

  public String getActiveChannel() {
    return activeChannel;
  }

  public void setActiveChannel(String activeChannel) {
    this.activeChannel = activeChannel;
  }

  public int getActiveServer() {
    return activeServer;
  }

  public Client getClient() {
    return Clients.INSTANCE.getClientFromInt(this.activeServer);
  }

  public void setActiveServer(int activeServer) {
    this.activeServer = activeServer;
  }

  public void save() {
    DataBaseConnector.INSTANCE.savePlayer(this);
  }

  public boolean isMuted() {
    return muted;
  }

  public void setMuted(boolean muted) {
    this.muted = muted;
  }

  public Calendar getUnmuteDate() {
    return unmuteDate;
  }

  public void setUnmuteDate(Calendar unmuteData) {
    this.unmuteDate = unmuteData;
  }

  public void removeChannel(String channel) {
    if (activeChannel == channel) {
      setActiveChannel("g");
    }
    channels.remove(channel);
  }

  public void setIgnoredPlayers(HashMap<String, Boolean> ignoredPlayers) {
    this.ignoredPlayers = ignoredPlayers;
  }

  public void addIgnoredPlayer(String player) {
    ignoredPlayers.put(player, true);
  }

  public void removeIgnoredPlayer(String player) {
    if (isIgnoring(player)) {
      ignoredPlayers.remove(player);
    }
  }

  public boolean isIgnoring(String player) {
    return ignoredPlayers.containsKey(player);
  }

  public boolean isProxying() {
    return isProxying;
  }

  public void setProxying(boolean isProxying) {
    this.isProxying = isProxying;
  }

  public String[] getPermissions() {
    return permissions;
  }

  public void setPermissions(String[] permissions) {
    this.permissions = permissions;
  }

  public ArrayList<String> getIgnoredPlayers() {
    ArrayList<String> ignoredPlayersList = new ArrayList<String>();

    for (String player : ignoredPlayers.keySet()) {
      ignoredPlayersList.add(player);
    }

    return ignoredPlayersList;
  }

  public void setLastPMFrom(String lastMessageFrom) {
    this.lastPMFrom = lastMessageFrom;
  }

  public String getLastPMFrom() {
    return lastPMFrom;
  }

  public void setInPM(boolean inPM) {
    this.talkingInPM = inPM;
  }

  public boolean getInPM() {
    return talkingInPM;
  }
}
