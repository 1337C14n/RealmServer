package minigame;

public class MiniGame {
  private String serverName;
  private String type;
  private volatile MiniGameStatus status;
  
  public MiniGame(String serverName, String type){
    this.type = type;
    this.serverName = serverName;
    status = MiniGameStatus.LOADING;
  }

  public String getServerName() {
    return serverName;
  }

  public String getType() {
    return type;
  }

  public MiniGameStatus getStatus() {
    return status;
  }

  public void setStatus(MiniGameStatus status) {
    this.status = status;
  }
  
}
