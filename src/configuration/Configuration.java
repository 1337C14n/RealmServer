package configuration;

import java.util.ArrayList;

public class Configuration {
  private String database;
  private String url;
  private String user;
  private String password;
  
  int interval;
  ArrayList<String> announcables;
  
  public int getInterval() {
    return interval;
  }
  public void setInterval(int interval) {
    this.interval = interval;
  }
  public ArrayList<String> getAnnouncables() {
    return announcables;
  }
  public void setAnnouncables(ArrayList<String> announcables) {
    this.announcables = announcables;
  }
  public String getDatabase() {
    return database;
  }
  public void setDatabase(String database) {
    this.database = database;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
}
