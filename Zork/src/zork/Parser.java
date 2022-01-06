package zork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Parser {
  private CommandWords commands; // holds all valid command words
  private Scanner in;
  private static TreeSet<String> ignoredWords = new TreeSet<String>(Arrays.asList("the", "with", "on", "a", "as", "against", "please", "i", "want", "to"));
  private static TreeSet<String> validDirections = new TreeSet<String>(Arrays.asList("north", "east", "south", "west", "southwest", "southeast", "northwest", "northeast"));
  public static final String yellow = "\u001B[33m";   //for the compiler arrow
  public static final String white = "\u001B[37m"; //also for the compiler arrow

  //test

  public Parser() {
    commands = new CommandWords();
    in = new Scanner(System.in);
  }

  public Command getCommand() throws java.io.IOException {
    String inputLine = "";
    ArrayList <String> words;

    
    System.out.println();
    System.out.print(yellow + "-->  " + white); // print prompt

    inputLine = in.nextLine().toLowerCase();

    inputLine = inputLine.trim().replace("  ", " "); 
    
    words = new ArrayList<String>(Arrays.asList(inputLine.split(" ")));

    translateDirections(words);

    
    if(validDirections.contains(words.get(0))){
      return new Command("go", words); 
    }
    

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

  private void translateDirections(ArrayList<String> words) {
    for(int i = 0; i <= words.size() - 1; i++){
      //IMPORTANT
      if(words.get(i).equals("n")){
        words.set(i, "north"); 
      }
      else if(words.get(i).equals("e")){
        words.set(i, "east"); 
      }
      else if(words.get(i).equals("s")){
        words.set(i, "south"); 
      }
      else if(words.get(i).equals("w")){
        words.set(i, "west"); 
      }
      else if(words.get(i).equals("ne")){
        words.set(i, "northeast"); 
      }
      else if(words.get(i).equals("nw")){
        words.set(i, "northwest"); 
      }
      else if(words.get(i).equals("se")){
        words.set(i, "southeast"); 
      }
      else if(words.get(i).equals("sw")){
        words.set(i, "southwest"); 
      }
    }
    
  }

  //checks if the commandWord is a valid direction so that the user can simply input the direction and no other words
  private boolean isValidDirection(String possibleDirection) {
    if(validDirections.contains(possibleDirection)){
      return true; 
    }
    return false; 
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }
}
