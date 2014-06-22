package main.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedQueue;

import main.logging.levels.Critical;
import main.logging.levels.Debug;
import main.logging.levels.Info;
import main.logging.levels.Mod;
import main.logging.levels.Warning;

public class Logger {
  
  public static final LogLevel CRITICAL = new Critical();
  public static final LogLevel WARNING  = new Warning();
  public static final LogLevel INFO = new Info();
  public static final LogLevel MOD = new Mod();
  public static final LogLevel DEBUG = new Debug();
  
  protected static ConcurrentLinkedQueue<LoggedEvent> logQueue;
  
  public Logger(){
    logQueue = new ConcurrentLinkedQueue<>();
    LogConsumer consumer = new LogConsumer();
    Thread consumerThread = new Thread(consumer);
    consumerThread.start();
  }
  
  public static void log(String message, LogLevel level){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    
    String loggedMessage = dateFormat.format(cal.getTime()) + " [" + level.getLevel() + "] " + message;
    
    LoggedEvent event = new LoggedEvent(level, message, cal.getTime());
    System.out.println(loggedMessage);
    logQueue.add(event);
    
  }
  
  public static void warning(String message){
    log(message, WARNING);
  }
  
  public static void info(String message){
    log(message, DEBUG);
  }
  
  public static void debug(String message){
    log(message, INFO);
  }
  
  public static void critical(String message){
    log(message, CRITICAL);
  }
}
