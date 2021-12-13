package zork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Parser {
  private CommandWords commands; // holds all valid command words
  private Scanner in;
  private static TreeSet<String> ignoredWords = new TreeSet<String>(Arrays.asList("the", "with", "on", "a", "as", "against", "please", "i", "want", "to"));

  public Parser() {
    commands = new CommandWords();
    in = new Scanner(System.in);
  }

  public Command getCommand() throws java.io.IOException {
    String inputLine = "";
    ArrayList <String> words;

    System.out.println();
    System.out.println();
    System.out.print("> "); // print prompt

    inputLine = in.nextLine().toLowerCase();

    inputLine = inputLine.trim().replace("  ", " "); 
    
   words = new ArrayList<String>(Arrays.asList(inputLine.split(" ")));

    for(int i = words.size() - 1; i >= 0; i--){
      //IMPORTANT
      if (ignoredWords.contains(words.get(i))){
        words.remove(i); 
      }
    }  

    String word1 = null; 

    if(words.size() != 0){
      word1 = words.remove(0); 
    }

    if (commands.isCommand(word1))
      return new Command(word1, words);
    else
      return new Command(null, words);
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }
}