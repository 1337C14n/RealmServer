package chat.privateMessage;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

public enum PrivateMessageHandler {
  INSTANCE;

  private DualHashBidiMap privateMessages = new DualHashBidiMap();

  Object mutex = new Object();

  public String checkForOpenPM(String player1) {
    synchronized (mutex) {
      if (privateMessages.containsKey(player1))
        return (String) privateMessages.get(player1);
      else if (privateMessages.inverseBidiMap().containsKey(player1))
        return (String) privateMessages.inverseBidiMap().get(player1);
    }
    return null;
  }

  public void addOpenPM(String player1, String player2) {
    synchronized (mutex) {
      privateMessages.put(player1, player2);
    }
  }

  public void closePM(String player) {
    // sets the pm to closed between the two players

  }

}
