package zork;

import java.util.ArrayList;

public class Command {
  private String commandWord;
  private ArrayList <String> extraWords = new ArrayList<String>();

  /**
   * Create a command object. First and second word must be supplied, but either
   * one (or both) can be null. The command word should be null to indicate that
   * this was a command that is not recognised by this game.
   */
  public Command(String firstWord, ArrayList<String> extraWords) {
    commandWord = firstWord;
    this.extraWords = extraWords;
  }

  /**
   * Return the command word (the first word) of this command. If the command was
   * not understood, the result is null.
   */
  public String getCommandWord() {
    return commandWord;
  }

  /**
   * Return the rest of the words of this command. Returns null if there are no other words
   */
  public ArrayList<String> getExtraWords() {
    return extraWords;
  }

  /**
   * Return true if this command was not understood.
   */
  public boolean isUnknown() {
    return (commandWord == null);
  }

  /**
   * Return true if the command has more than one word
   */
  public boolean hasExtraWords() {
    return (extraWords.size() != 0);
  }
}
