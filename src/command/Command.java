package command;

import packets.CommandMessage;
import packets.Packet;
import packets.PlayerMessage;

public abstract class Command {
  
  protected CommandMessage message;
  protected String[] args;
  protected int argsLength;
  protected String sender;
  
  public Command(CommandMessage message){
    this.message = message;
    args = message.getArgs();
    argsLength = args.length;
    sender = message.getSender();
  }
  
  public abstract Object execute();
  
  public Packet response(String response){
    return new PlayerMessage(message.getSender(), response);
  }
}
