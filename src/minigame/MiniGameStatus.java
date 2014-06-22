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
  
  public String getStatusMessage(){
    switch (status){
    case 0:
      return "Server is loading";
    case 1:
      return "Waiting for players";
    case 2:
      return "The game has started";
    case 3:
      return "The game has ended";
    case 4:
      return "The game is open";
    default:
      return "Something must have gone wrong";  
    }
  }
}
