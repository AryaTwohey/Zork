package zork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();
  private Parser parser;
  private Room currentRoom;
  public static final String yellow = "\u001B[33m";      //for the welcome message
  public static final String white = "\u001B[0m";        //also for the welcome message



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
   * Main play routine. Loops until end of play.
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
    String quit =  "Thank you for playing. Good Bye.";

    for(int i = 0; i < quit.length(); i++){
      System.out.printf("%c", quit.charAt(i));
      Thread.sleep(15);
    }
    System.out.println();
    System.out.println();
  }

  /**
   * Print out the opening message for the player.
   * @throws InterruptedException
   */
  private void printWelcome() throws InterruptedException {
    
  String welcome = "Welcome To Zork!!!";
  String creators = "A text-adventure game created by Arya, Arman, Lara and Muriel!!!";
  String help = "Type help to see the commands";
  String line = yellow + "________________________________________________________________" + white;

  //Cool Printable Message

  System.out.println();
  System.out.println();
  //line

    for(int i = 0; i < line.length(); i++){

    System.out.printf("%c", line.charAt(i));
    Thread.sleep(10);
    }
    System.out.println();
    System.out.println();

    for(int i = 0; i < welcome.length(); i++){
      System.out.printf("%c", welcome.charAt(i));
      Thread.sleep(10);
    }
    System.out.println();
    System.out.println();

    for(int i = 0; i < creators.length(); i++){
      System.out.printf("%c", creators.charAt(i));
      Thread.sleep(10);
    }

    System.out.println();
    System.out.println();

    for(int i = 0; i < help.length(); i ++){
      System.out.printf("%c", help.charAt(i));
      Thread.sleep(10);
    }

    System.out.println();
    System.out.println();

    for(int i = 0; i < line.length(); i++){
    
      System.out.printf("%c", line.charAt(i));
      Thread.sleep(10);
    }

    System.out.println();
    System.out.println();

  }

  /**
   * Given a command, process (that is: execute) the command. If this command ends
   * the game, true is returned, otherwise false is returned.
   */
  private boolean processCommand(Command command) {
    if (command.isUnknown()) {
      System.out.println("I don't know what you mean...");
      return false;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help"))
      printHelp();
    else if (commandWord.equals("go"))
      goRoom(command);
    else if (commandWord.equals("quit")) {
      if (command.hasExtraWords())
        System.out.println("Quit what?");
      else
        return true; // signal that we want to quit
    } else if (commandWord.equals("eat")) {
      System.out.println("Eat?!? Are you serious");
    }
    else if(commandWord.equals("climb")){
      goRoom(command);
    }
    else if(commandWord.equals("take")){
      goRoom(command);
    }
    else if(commandWord.equals("drop")){
      goRoom(command);
    }
    else if(commandWord.equals("kill")){
      goRoom(command);
    }
    else if(commandWord.equals("search")){
      goRoom(command);
    }
    else if(commandWord.equals("read")){
      goRoom(command);
    }
    else if(commandWord.equals("run")){
      goRoom(command);
    }
    else if(commandWord.equals("shoot")){
      goRoom(command);
    }
    else if(commandWord.equals("hit")){
      goRoom(command);
    }
    else if(commandWord.equals("stab")){
      goRoom(command);
    }
    return false;
  }

  // implementations of user commands:

  /**
   * Print out some help information. Here we print some stupid, cryptic message
   * and a list of the command words.
   */
  private void printHelp() {
    System.out.println("Now is not a good time to be lost!");
    System.out.println("You better get to thinking.");
    System.out.println();
    System.out.println("Your command words are:");
    parser.showCommands();
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(Command command) {
    if (!command.hasExtraWords()) {
      // if there is no second word, we don't know where to go...
      System.out.println("Go Where?");
      return;
    }

    ArrayList<String> rest = command.getExtraWords();

    String direction = rest.get(0); 

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null)
      System.out.println("There is no door!");
    else {
      currentRoom = nextRoom;
      System.out.println(currentRoom.longDescription());
    }
  }
}
