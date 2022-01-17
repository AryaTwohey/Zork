package zork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Parser extends CommandWords {
  private CommandWords commands; // holds all valid command words
  private Scanner in;
  private static TreeSet<String> ignoredWords = new TreeSet<String>(Arrays.asList("the", "with", "on", "a", "as", "using", "against", "using", "can", "please", "i", "want", "to", "at", "you", "freddy", "krueger", "ghostface", "minion", "doll", "micheal", "myers", "shreck", "boss", "doll", "pennywise")); //all the words that the parser nows to ignore when they are inputted 
  private static TreeSet<String> validDirections = new TreeSet<String>(Arrays.asList("north", "east", "south", "west", "southwest", "southeast", "northwest", "northeast")); //all the valid directions that the parser should understand
  public static final String yellow = "\u001B[33m";   //for the compiler arrow
  public static final String white = "\u001B[37m"; //also for the compiler arrow
  
  //constructor
  public Parser() {
    commands = new CommandWords();
    in = new Scanner(System.in);
  }

  /**
   * grabs the text that the player wrote  
   * gets rid of all spaces and turns the player's command into an Arraylist of strings (for each word)
   * then it calls translate directions (explained at that method)
   * Then it will check whether the first string in our list is a direction or not 
   * if it is a direction it will automatically return the command with a go as the primary command word 
   * next we start at the beginning of the ArrayList and check whether the first string is a valid command (and so on)
   * if it is not a valid command it will remove it from the list until we find a valid command or until there is only one string in the list
   * next we go through the array list and remove all the words that the parser is told to ignore (from the ignored words tree set)
   * next we take the first word of the words ArrayList and make that word1 - this is the command word 
   * @return a new command with a command word (either null or not) and extra words
   * @throws java.io.IOException
   */
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
    
    while(!isCommand(words.get(0)) && words.size() > 1){
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

  /**
   * this is a void method which will go through the words array and turn all the one letter translation of the direction into full length translation of the direction
   * eg. n will be north and sw will be southwest
   * and if the user simply types nothing ("") the game will say "time is ticking, make your choice" - to prompt them into writing something meaningful 
   * @param words
   */
  private void translateDirections(ArrayList<String> words) {

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
      }                                                                                            //if it is valid or invalid
    }
  }

  /**
   * Print out a list of valid command words.
   */
  public void showCommands() {
    commands.showAll();
  }
}
