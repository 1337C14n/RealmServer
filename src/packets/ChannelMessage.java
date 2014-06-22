package packets;

import java.util.ArrayList;

public class ChannelMessage extends ChatPacket {

  /**
   * 
   */
  private static final long serialVersionUID = 2340408876568447228L;
  
  private String sender;
  private ArrayList<String> recipients = new ArrayList<String>();
  private String message;
  private String channelName;
  private String playerPrefix;
  private boolean prefixEnabled;
  private boolean senderIsModerator = false;
  
  public ChannelMessage(String channelName, String playerPrefix, String sender, ArrayList<String> recipients, String message, boolean prefixEnabled){
    this.recipients = recipients;
    this.message = message;
    this.channelName = channelName;
    this.sender = sender;
    this.prefixEnabled = prefixEnabled;
    this.playerPrefix = playerPrefix;
  }

  public ArrayList<String> getRecipients() {
    return recipients;
  }

  public String getMessage() {
    return message;
  }
  
  public String getChannelName() {
    return channelName;
  }

  public String getSender() {
    return sender;
  }

  public boolean isPrefixEnabled() {
    return prefixEnabled;
  }

  public void setPrefixEnabled(boolean prefixEnabled) {
    this.prefixEnabled = prefixEnabled;
  }

  public String getPlayerPrefix() {
    return playerPrefix;
  }

  public boolean isSenderIsModerator() {
    return senderIsModerator;
  }

  public void setSenderIsModerator(boolean senderIsModerator) {
    this.senderIsModerator = senderIsModerator;
  }
  

}
