package zork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;    //not sure if we actaully need these 
import java.util.HashMap;
import java.util.TreeSet;   //code works without them

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();
  private Parser parser;
  private Room currentRoom;
  Inventory playerInventory;
  Item item;
  
  
  public static final String yellow = "\u001B[33m";       //for the welcome message
  public static final String white = "\u001B[0m";        //also for the welcome message
  public static final String blue = "\u001B[34m";       //for quit message 
  public static final String red = "\u001B[31m";       //for red coloured text (blood)


  /**
   * Create the game and initialize its internal map.
   * fred is hot
   */
  public Game() {
    try {
      initRooms("src\\zork\\data\\rooms.json");
      currentRoom = roomMap.get("Outside Entrance");
    } catch (Exception e) {
      e.printStackTrace();
    }
   parser = new Parser();
   playerInventory = new Inventory(1000); 

  }

  private void initRooms(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonRooms = (JSONArray) json.get("rooms");

    for (Object roomObj : jsonRooms) {
      Room room = new Room();
      String roomName = (String) ((JSONObject) roomObj).get("name");
      String roomId = (String) ((JSONObject) roomObj).get("id");
      String roomDescription = (String) ((JSONObject) roomObj).get("description");
      room.setDescription(roomDescription);
      room.setRoomName(roomName);

      JSONArray jsonExits = (JSONArray) ((JSONObject) roomObj).get("exits");
      ArrayList<Exit> exits = new ArrayList<Exit>();
      for (Object exitObj : jsonExits) {
        String direction = (String) ((JSONObject) exitObj).get("direction");
        String adjacentRoom = (String) ((JSONObject) exitObj).get("adjacentRoom");
        String keyId = (String) ((JSONObject) exitObj).get("keyId");
        Boolean isLocked = (Boolean) ((JSONObject) exitObj).get("isLocked");
        Boolean isOpen = (Boolean) ((JSONObject) exitObj).get("isOpen");
        Exit exit = new Exit(direction, adjacentRoom, isLocked, keyId, isOpen);
        exits.add(exit);
      }
      room.setExits(exits);
      roomMap.put(roomId, room);
    }
  }

  /**
   * Required for printable messges, doesnt do anything else
   * @throws InterruptedException
   */
  public void play() throws InterruptedException {
    printWelcome();

    boolean finished = false;
    while (!finished) {
      Command command;
      try {
        command = parser.getCommand();
        finished = processCommand(command);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    String quit =  blue + "Thank you for playing. Good Bye." + white;
    String threeDots = "...";
    System.out.println();
    System.out.println();
    System.out.println();
    for(int i = 0; i < quit.length(); i++){
      System.out.printf("%c", quit.charAt(i));
      Thread.sleep(15);
    }
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.print("TERMINATING COMPILER");

    for(int i = 0; i < threeDots.length(); i++){

        System.out.printf("%c", threeDots.charAt(i));
        Thread.sleep(1500);
    }

    System.out.println();
    
    System.out.println("TERMINATION COMPLETE");
    System.out.println();
  }

  /**
   * Print out the opening message for the player.
   * @throws InterruptedException
   */
  private void printWelcome() throws InterruptedException {
    
  String welcome = "Welcome To Zork!!!";
  String creators = "A text-adventure game created by Arya, Arman, Lara and Muriel!!!";
  String help = "Type 'help' to see the commands";
  String line = yellow + "________________________________________________________________" + white;

  //Cool Printable Message

  System.out.println();
  System.out.println();
  //line

    for(int i = 0; i < line.length(); i++){

    System.out.printf("%c", line.charAt(i));
    Thread.sleep(5);
    }
    System.out.println();
    System.out.println();

    for(int i = 0; i < welcome.length(); i++){

      System.out.printf("%c", welcome.charAt(i));
      Thread.sleep(5);
    }
   
    System.out.println();
    System.out.println();

    for(int i = 0; i < creators.length(); i++){

      System.out.printf("%c", creators.charAt(i));
      Thread.sleep(5);
    }

    System.out.println();
    System.out.println();

    for(int i = 0; i < help.length(); i ++){

      System.out.printf("%c", help.charAt(i));
      Thread.sleep(5);
    }

    System.out.println();
    System.out.println();

    for(int i = 0; i < line.length(); i++){
    
      System.out.printf("%c", line.charAt(i));
      Thread.sleep(5);
    }
    System.out.println();
    System.out.println();

    /**Sorry for all the strings
     * I just wanted to have the "blood" text coloured in red
     */
  
    String firstScentence = "In front of you there is a large house with no lights on." ;
    String secondScentence ="The windows are boarded up and you can hear squeaking and faint screaming."; 
    String thirdScentence = "To the North of you there is an open door covered in ";
    String forthScentence = "...the ";
    String fifthScentence = " of your friend.";
    String blood = red + "blood" + white;
    String exits = "Exits: North ";

    System.out.println(firstScentence);
    System.out.println(secondScentence);
    System.out.print(thirdScentence);
    System.out.print(blood);
    System.out.print(forthScentence);
    System.out.print(blood);
    System.out.println(fifthScentence);
    System.out.println();
    System.out.println(exits);
    System.out.println();
  }

  /**
   * Given a command, process (that is: execute) the command. If this command ends
   * the game, true is returned, otherwise false is returned.
   * 
   * @throws InterruptedException   //this is for the printhelp message, dont worry it doenst do anything bad
   */
  private boolean processCommand(Command command) throws InterruptedException {
    
    
    if (command.isUnknown()) {
      
      System.out.println();
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();

    if (commandWord.equals("help"))
      printHelp();
    else if (commandWord.equals("go") || commandWord.equals("move"))
      goRoom(command);
    else if (commandWord.equals("quit")) {
      if (command.hasExtraWords()){
        System.out.println();
        System.out.println("Quit what?");
       } else
        return true; // signal that we want to quit
    } else if (commandWord.equals("eat")) {
      System.out.println();
      System.out.println("Eat?!? Are you serious");
    }
    else if(commandWord.equals("take")){
      playerInventory.addItem(command, item); 
    }
    else if(commandWord.equals("drop")){
      playerInventory.dropItem(command);
    }
    else if(commandWord.equals("kill")){
      System.out.println();
      System.out.println("Kill what?");
    }
    else if(commandWord.equals("search")){
      System.out.println();
      System.out.println("There is nothing to search for");
    }
    else if(commandWord.equals("read")){
      System.out.println();
      System.out.println("There is nothing to read...");
    }
    else if(commandWord.equals("run")){
      goRoom(command);
    }
    else if(commandWord.equals("shoot")){
      System.out.println();
      System.out.println("Shoot what?");
    }
    else if(commandWord.equals("hit")){
      System.out.println();
      System.out.println("Hit what?");
    }
    else if(commandWord.equals("stab")){
      System.out.println();
      System.out.println("Stab what?");

    }
    else if(commandWord.equals("inventory") || commandWord.equals("display")){ 
      System.out.println();
      System.out.println("*Not Finished Yet");
      System.out.println();
      System.out.println("-Developers");

      displayInventory(command);

    }else if(commandWord.equals("restart") || commandWord.equals("Restart")){

      



    }
    else if(commandWord.equals("hello")){

    int result = (int)(Math.random() * 3);
      if(result == 1){

        System.out.println();
        System.out.println("Hi, how are you?");
        System.out.println();

      }else if(result == 2){

        System.out.println();
        System.out.println("Whats Up?");
        System.out.println();
      }else{

        System.out.println();
        System.out.println("Whats Good?");
        System.out.println();

      }
    }
    else if(commandWord.equals("fred")){
      System.out.println();
      System.out.println();
      
      String importantMessage = "Fred is hot";

        for(int i = 0 ; i < importantMessage.length(); i++){

          System.out.printf("%c", importantMessage.charAt(i));
          Thread.sleep(500);

        }
        System.out.println();
        System.out.println();
    }
    else if(commandWord.equals("winson")){



      int ran = (int) (Math.random() * 2);
      if(ran == 0){
        String quotes = "''";

        System.out.println();
        System.out.print("Coincidence: ");
        System.out.println(quotes + "A situation in which events happen at the same time in a way that is not planned or expected." + quotes);
        System.out.println();
        System.out.println("...however, I think math");
        System.out.println();
        System.out.println("    -Greg Winson 2011-2021");
      } 
      else{
        System.out.println();
        System.out.println("Remember, the most common mistakes in Math is arithmetic involving negative numbers."); 
        System.out.println();
        System.out.println("     -Greg Winson 2011-2021");
      }
    }
      
    return false;
  }

  // implementations of user commands:


  /**
   * Print out some help information and a list of the command words.
   * 
   * @throws InterruptedException // this is for the printed message it doesnt do anything 
   */
  private void printHelp() throws InterruptedException {
    String helperMessage = "Your command words are below, use them to win the game ";

    System.out.println();
    System.out.println();

      for(int i  = 0; i < helperMessage.length(); i++){

      System.out.printf("%c", helperMessage.charAt(i));
    
      Thread.sleep(15);
      }
      System.out.println();
      System.out.println();

      parser.showCommands();
      
      System.out.println();
      System.out.println();

  }
  public void displayInventory(Command command){

    for(int i = 0; i < playerInventory.toString().length(); i++){ 
      System.out.println(i + " ");
    
  }
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(Command command) {
    if (!command.hasExtraWords()) {
      // if there is no second word, we don't know where to go...
      System.out.println();
      System.out.println("Go where?");
      return;
    }

    ArrayList<String> rest = command.getExtraWords();

    String direction = rest.get(0); 

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null){
      System.out.println();
      System.out.println("There is no path or door!");
     } else {
      currentRoom = nextRoom;
      System.out.println(currentRoom.longDescription());
      }
  }
}