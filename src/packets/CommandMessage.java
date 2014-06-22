package packets;


public class CommandMessage extends ChatPacket {
  /**
   * 
   */
  private static final long serialVersionUID = 6270298894273746939L;
  String sender;
  String command;
  String[] args;
  
  public CommandMessage(String sender, String command, String... args){
    this.command = command;
    this.args = args;
    this.sender = sender;
  }
  
  public String getCommand() {
    return command;
  }

  public String[] getArgs() {
    return args;
  }

  public String getSender() {
    return sender;
  }
  
  @Override
  public String toString(){
    String temp = "CommandMessage: Sender: " + sender + ", Command: " + command + ", Args: ";
    
    for(String arg : args){
      temp += arg + ", ";
    }
    
    return temp;
  }

  public void setArgs(String[] args) {
    this.args = args;
  }
}
