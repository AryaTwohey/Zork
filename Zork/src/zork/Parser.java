package zork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Parser {
  private CommandWords commands; // holds all valid command words
  private Scanner in;
  private ArrayList <String> rest;  
  private static TreeSet<String> ignoredWords = new TreeSet<String>(Arrays.asList("the", "with", "on", "a", "as", "against"));

  public Parser() {
    commands = new CommandWords();
    rest = new ArrayList<String>(); 
    in = new Scanner(System.in);
  }

  public Command getCommand() throws java.io.IOException {
    String inputLine = "";
    String[] words;


    System.out.print("> "); // print prompt

    inputLine = in.nextLine();

    words = inputLine.split(" ");

    String word1 = words[0];

    for(int i = 0; i < words.length - 1; i++){
        while(i != 0){
          //IMPORTANT
          if (!ignoredWords.contains(words[i])){
            rest.add(words[i]); 
          }
        }
    }  

    if (words.length <= 1)
      rest.clear();


    if (commands.isCommand(word1))
      return new Command(word1, rest);
    else
      return new Command(null, rest);
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }
}
