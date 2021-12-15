package zork;

public class CommandWords {
  // a constant array that holds all valid command words
  private static final String validCommands[] = { "go", "quit", "help", "eat", "take", "drop", "kill", "search", "read", "run", "shoot", "hit", "stab", "inventory", "hello", "fred", "winson"};
                                                                                                                                                                  //these commands are not included in printhelp
  /**                                                                                                                                                             //so they are hidden from the users basic knowledge of the game
   * Constructor - initialise the command words.
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
    for (String c : validCommands) {
      if(!(c.equals("winson") || c.equals("fred") || c.equals("hello")))
        System.out.print(c + "  ");
    }
    System.out.println();
  }
}
