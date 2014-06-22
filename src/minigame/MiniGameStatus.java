package minigame;

public enum MiniGameStatus {
  LOADING(0),
  WAITING_FOR_PLAYERS(1),
  STARTED(2),
  ENDED(3),
  OPEN(4)
  ;
  
  private final int status;
  
  MiniGameStatus(int status){
    this.status = status;
  }
  
  public int getStatus() {
    return status;
  }
}
