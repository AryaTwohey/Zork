package zork;

import java.util.Arrays;
import java.util.TreeSet;

public class CommandWords {
  // a constant array that holds all valid command words
  public static final String yellow = "\u001B[33m";      //for the square brackets in print help
  public static final String white = "\u001B[0m";        //for square brakcets

<<<<<<< HEAD
  private static final String validCommands[] = { "go", "move", "quit", "help", "eat", "take", "drop", "search", "read", "run", "shoot", "hit", "stab", "fire", "use", "inventory", "display", "reset", "restart", "space", "fred", "winson", ""};
=======
  private static final String validCommands[] = { "go", "move", "quit", "help", "eat", "take", "drop", "kill", "search", "read", "run", "shoot", "hit", "stab", "fire", "use", "attack", "inventory", "display", "reset", "restart", "space", "inspect", "fred", "winson", ""};
>>>>>>> ff501fd1adb317671b030d476931cdda635d3eb9
  //some of these commands are not included in printhelp, so they are hidden from the basic knowledge of the game

  /**                                                                                                                                                            
   * 
   * Constructor - initialize the command words.
   */
  public CommandWords() {
    // nothing to do at the moment...
  }

  /**
   * Check whether a given String is a valid command word. Return true if it is,
   * false if it isn't.
   **/
  public boolean isCommand(String aString) {
    for (String c : validCommands) {
      if (c.equals(aString))
        return true;
    }
    // if we get here, the string was not found in the commands
    return false;
  }

  /*
   * Print all valid commands to System.out.
   */
  public void showAll() {
    String openBracket = yellow + "[" + white;
    String closeBracket = yellow + "]" + white;
    String yellowComma = yellow + "," + white;
    
    System.out.print(openBracket + "  ");

    for (String c : validCommands) {                                                                //for the time is ticking message
      if(!(c.equals("winson") || c.equals("fred") || c.equals("display") || c.equals("restart") || c.equals("")))
        System.out.print(c + yellowComma + "  ");
    }
    System.out.print(closeBracket);

    System.out.println();
  }
}
