package command;

import main.Player;
import main.PlayerHandler;
import packets.CommandMessage;
import packets.Packet;
import packets.PlayerMessage;

public abstract class Command {
  
  protected CommandMessage message;
  protected String[] args;
  protected int argsLength;
  protected Player sender;
  
  public Command(CommandMessage message){
    this.message = message;
    this.args = message.getArgs();
    this.argsLength = args.length;
    
    this.sender = PlayerHandler.INSTANCE.getPlayerFromPlayerName(message.getSender()); 
  }
  
  public abstract Object execute();
  
  public Packet response(String response){
    return new PlayerMessage(message.getSender(), response);
  }
}
