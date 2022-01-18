/**
 * Arman made some changes to Command Words to make it work with current version of game
 */
package zork;

import java.util.Arrays;
import java.util.TreeSet;

public class CommandWords {
  // a constant array that holds all valid command words
  public static final String yellow = "\u001B[33m";      //for the square brackets in print help
  public static final String white = "\u001B[0m";        //for square brakcets

  private static final String validCommands[] = { "go", "move", "quit", "help", "eat", "take","inspect", "drop", "search", "read", "run", "shoot", "hit", "stab", "fire", "use", "inventory", "display", "reset", "restart", "space", "fred", "winson", "", "heal", "restore", "kill"};
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
    // if we are out of the for loop and the string still hasn't been found it means that it is not a valid command, therefor it returns false
    return false;
  }

  /*
   * Print all valid commands to System.out.
   * If commands are hidden, the forEach loop ignores them
   */

   
  /**
   * Arya and Arman worked on showAll 
   */
  public void showAll() {
    String openBracket = yellow + "[" + white;
    String closeBracket = yellow + "]" + white;
    String yellowComma = yellow + "," + white;
    
    System.out.print(openBracket + "  ");

    for (String c : validCommands) {                                                                //for the time is ticking message
      if(!(c.equals("winson") || c.equals("fred") || c.equals("display") || c.equals("restart") || c.equals("") || c.equals("restore")))
        System.out.print(c + yellowComma + "  ");
    }
    System.out.print(closeBracket);

    System.out.println();
  }
}
