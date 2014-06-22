package chat;

import java.util.Date;

public class ChatMessage {
  private String player;
  private String text;
  private Date time;
  
  public ChatMessage(String player, String text, Date time){
    this.player = player;
    this.text = text;
    this.time = time;
  }

  public String getPlayer() {
    return player;
  }

  public String getText() {
    return text;
  }

  public Date getTime() {
    return time;
  }
  
}
