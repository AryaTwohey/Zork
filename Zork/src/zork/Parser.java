package zork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Parser extends CommandWords {
  private CommandWords commands; // holds all valid command words
  private Scanner in;
  private static TreeSet<String> ignoredWords = new TreeSet<String>(Arrays.asList("the", "with", "on", "a", "as", "using", "against", "using", "can", "please", "i", "want", "to", "you"));
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

    
    System.out.println(); //formatting
    System.out.println();
    System.out.print(yellow + "-->  " + white); // print prompt

    inputLine = in.nextLine().toLowerCase();

    inputLine = inputLine.trim().replace("  ", " "); 
    
    words = new ArrayList<String>(Arrays.asList(inputLine.split(" ")));

    translateDirections(words);

    
    if(validDirections.contains(words.get(0))){
      return new Command("go", words); 
    }
    
    while(!isCommand(words.get(0)) && words.size() > 0){
      words.remove(0); 
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
    String validDirections = "neswnenwsesw";    //used for shorter movement commands

    for(int i = 0; i <= words.size() - 1; i++){

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
      }else if(words.get(i).length() == 0){
        System.out.println();
        System.out.println("Time is ticking, make your choice...");
        System.out.println();
      }                                                             //this length limit is so that it doesnt mix up other commands with directions
      else if(validDirections.indexOf(i) < 0 && words.get(i).length() == 1 || words.get(i).length() < 3){ //shows the invalid direction being inputted

        System.out.println();
        System.out.println(words.get(i).toString() + " is not a valid direction");    //this shows the shortened 
        System.out.println();                                                         //direction like ne (northeast) or s (south)
      }                                                                               //if it is valid or invalid
    }
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }
}
