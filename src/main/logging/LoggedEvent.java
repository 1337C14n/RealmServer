package main.logging;

import java.util.Date;

public class LoggedEvent {
  private LogLevel level;
  private String text;
  private Date date;
  
  public LoggedEvent(LogLevel level, String text, Date date){
    this.level = level;
    this.text = text;
    this.date = date;
  }

  public LogLevel getLevel() {
    return level;
  }

  public String getText() {
    return text;
  }

  public Date getDate() {
    return date;
  }
  
}
