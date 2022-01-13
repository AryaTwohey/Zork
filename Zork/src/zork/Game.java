package zork;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

  public static HashMap<String, Room> roomMap = new HashMap<String, Room>();
  public static HashMap<String, Item> itemMap = new HashMap<String, Item>();
  private Parser parser;
  private Room currentRoom;
  Inventory playerInventory;
  Item item;
  String weapons[] = { "pistol", "bat", "ak 47", "pitchfork", "plastic spoon", "knife", "sword" };
  int playerHealth = 500; 

  public static final String yellow = "\u001B[33m"; // for the welcome message
  public static final String white = "\u001B[0m"; // also for the welcome message
  public static final String blue = "\u001B[34m"; // for quit message
  public static final String red = "\u001B[31m"; // for red coloured text (blood)
  public static final String green = "\u001B[32m";


  /**
   * Create the game and initialize its internal map.
   * 
   */
  public Game() {
    try {
      initRooms("src\\zork\\data\\rooms.json");
      initItems("src\\zork\\data\\items.json");
      initCharacters("src\\zork\\data\\characters.json");
      initWeapons("src\\zork\\data\\weapons.json");
      currentRoom = roomMap.get("Outside Entrance");

    } catch (Exception e) {
      e.printStackTrace();
    } 
    parser = new Parser();

    /* * * * * * * * * * * *  * * * * * * * * * * * * *
     * 8633g - - > Sum of all items weights           *
     * 6475 - - > Approximatley 75% of max capacity   *
     * I (Arya) think that this should be the max-    *
     * carrying capacity for a inventory              *
     * * * * * * * * * * * * * * * * * * * * * * * * *    
    */
    playerInventory = new Inventory(6475);
  }

  private void reset() {
    try {
      initRooms("src\\zork\\data\\rooms.json");
      initItems("src\\zork\\data\\items.json");
      initCharacters("src\\zork\\data\\characters.json");
      initWeapons("src\\zork\\data\\weapons.json");
      currentRoom = roomMap.get("Outside Entrance");
    } catch (Exception e) {
      e.printStackTrace();
    }
    parser = new Parser();
    playerInventory = new Inventory(6475);

  }

  private void initItems(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String stringJson = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(stringJson);

    JSONArray itemsJson = (JSONArray) json.get("items");

    for (Object obj : itemsJson) {
      Item item = new Item();
      String itemName = (String) ((JSONObject) obj).get("name");
      String itemId = (String) ((JSONObject) obj).get("id");
      int itemWeight = Integer.parseInt((String) ((JSONObject) obj).get("Weight"));
      String itemDescription = (String) ((JSONObject) obj).get("description");
      String itemStartingRoom = (String) ((JSONObject) obj).get("starting location");
      String sIsNote = (String) ((JSONObject) obj).get("isNote");

      item.setName(itemName);
      item.setDescription(itemDescription);
      item.setWeight(itemWeight);
      if(sIsNote != null){
        item.setIsNote(Boolean.parseBoolean(sIsNote)); 
      }
      roomMap.get(itemStartingRoom).addItem(item);

    }
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

      room.setRoomName(roomName);
      room.setExits(exits);
      roomMap.put(roomId, room);
    }
  }

  private void initCharacters(String fileName) throws Exception {
    Path path = Path.of(fileName);
    String jsonString = Files.readString(path);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsonString);

    JSONArray jsonChar = (JSONArray) json.get("characters");

    for (Object charObj : jsonChar) {
      Character character = new Character();
      String charName = (String) ((JSONObject) charObj).get("name");
      String charId = (String) ((JSONObject) charObj).get("id");
      String charDescription = (String) ((JSONObject) charObj).get("description");
      String charLocation = (String) ((JSONObject) charObj).get("starting location");
      int charHealth = Integer.parseInt((String) ((JSONObject) charObj).get("Health"));
      int charDamage = Integer.parseInt((String) ((JSONObject) charObj).get("Damage"));
      
      character.setLocation(charLocation); 
      character.setDescription(charDescription);
      character.setName(charName);
      character.setHealth(charHealth);
      character.setDamage(charDamage); 
      roomMap.get(charLocation).addCharacter(character);

      // add characters to room - like items see init items

    }
  }
    
    private void initWeapons(String fileName) throws Exception {
      Path path = Path.of(fileName);
      String stringJson = Files.readString(path);
      JSONParser parser = new JSONParser();
      JSONObject json = (JSONObject) parser.parse(stringJson);

      JSONArray weaponsJson = (JSONArray) json.get("weapons");

      for (Object weaponobj : weaponsJson) {
        Weapon weapon = new Weapon();
        String weaponName = (String) ((JSONObject) weaponobj).get("name");
        String weaponId = (String) ((JSONObject) weaponobj).get("id");
        int weaponWeight = Integer.parseInt((String) ((JSONObject) weaponobj).get("weight"));
        String weaponDescription = (String) ((JSONObject) weaponobj).get("description");
        String weaponStartingRoom = (String) ((JSONObject) weaponobj).get("starting location");
        Integer weaponDamage = Integer.parseInt((String) ((JSONObject) weaponobj).get("damage"));

        weapon.setName(weaponName);
        weapon.setDescription(weaponDescription);
        weapon.setWeight(weaponWeight);
        weapon.setDamage(weaponDamage);
        roomMap.get(weaponStartingRoom).addItem(weapon);

      }
  }

  /**
   * Required for printable messges, doesnt do anything else
   * 
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
    String quit = blue + "Thank you for playing. Good Bye." + white;
    String threeDots = "...";
    System.out.println();
    System.out.println();
    System.out.println();
    for (int i = 0; i < quit.length(); i++) {
      System.out.printf("%c", quit.charAt(i));
      Thread.sleep(15);
    }
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.print("TERMINATING COMPILER");

    for (int i = 0; i < threeDots.length(); i++) {

      System.out.printf("%c", threeDots.charAt(i));
      Thread.sleep(1500);
    }

    System.out.println();

    System.out.println("TERMINATION COMPLETE");
    System.out.println();
  }

  /**
   * Print out the opening message for the player.
   * 
   * @throws InterruptedException
   */
  private void printWelcome() throws InterruptedException {

    String welcome = "Welcome To Zork!!!";
    String creators = "A text-adventure game created by Arya, Arman, Lara and Muriel!!!";
    String help = "Type 'help' to see the commands";
    String line = yellow + "________________________________________________________________" + white;

    // Cool Printable Message

    System.out.println();
    System.out.println();
    // line

    for (int i = 0; i < line.length(); i++) {

      System.out.printf("%c", line.charAt(i));
      Thread.sleep(5);
    }
    System.out.println();
    System.out.println();

    for (int i = 0; i < welcome.length(); i++) {

      System.out.printf("%c", welcome.charAt(i));
      Thread.sleep(5);
    }

    System.out.println();
    System.out.println();

    for (int i = 0; i < creators.length(); i++) {

      System.out.printf("%c", creators.charAt(i));
      Thread.sleep(5);
    }

    System.out.println();
    System.out.println();

    for (int i = 0; i < help.length(); i++) {

      System.out.printf("%c", help.charAt(i));
      Thread.sleep(5);
    }

    System.out.println();
    System.out.println();

    for (int i = 0; i < line.length(); i++) {

      System.out.printf("%c", line.charAt(i));
      Thread.sleep(5);
    }
    System.out.println();
    System.out.println();

    /**
     * Sorry for all the strings
     * I just wanted to have the "blood" text coloured in red
     */

    String firstScentence = "In front of you there is a large house with no lights on.";
    String secondScentence = "The windows are boarded up and you can hear squeaking and faint screaming.";
    String thirdScentence = "To the North of you there is an open door covered in ";
    String blood = red + "blood" + white + "...";
    String exits = "Exits: North ";

    System.out.println(firstScentence);
    System.out.println(secondScentence);
    System.out.print(thirdScentence);
    System.out.print(blood);
    System.out.println();
    System.out.println();
    System.out.println(exits);
    System.out.println();
  }

  /**
   * Given a command, process (that is: execute) the command. If this command ends
   * the game, true is returned, otherwise false is returned.
   * 
   * @throws InterruptedException //this is for the printhelp message, dont worry
   *                              it doenst do anything bad
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
    else if (commandWord.equals("go") || commandWord.equals("move") || commandWord.equals("run"))
      goRoom(command);
    else if (commandWord.equals("quit")) {
      return true; // signal that we want to quit
    } else if (commandWord.equals("eat")) {
      System.out.println();
      System.out.println("Eat?!? Are you serious?!");
    } else if (commandWord.equals("take")) {
      takeItem(command);
    } else if (commandWord.equals("drop")) {
      dropItem(command);
    } else if (commandWord.equals("kill") || commandWord.equals("shoot") || commandWord.equals("fire")
        || commandWord.equals("hit") || commandWord.equals("stab") || commandWord.equals("use")) {
      System.out.println();
      attack(command);
      System.out.println(currentRoom.exits());
      System.out.println();
    } else if (commandWord.equals("search")) {
      System.out.println();
      search(command);
      System.out.println(currentRoom.exits());
      System.out.println();
    } else if (commandWord.equals("read")) {
      System.out.println();
      read(command);
      System.out.println(currentRoom.exits());
      System.out.println(); 
    } else if (commandWord.equals("inventory") || commandWord.equals("display")) {
      displayInventory();
    }else if (commandWord.equals("space")) {
      inventorySpace(command);
      System.out.println(currentRoom.exits());
     } else if(commandWord.equals("inspect")){

    //  inspectTheItem(command);



    
    } else if (commandWord.equals("restart") || commandWord.equals("reset")) {

      System.out.println();
      System.out.println("Your game is being reset...");
      reset();

      String welcome = "Welcome To Zork!!!";
      String creators = "A text-adventure game created by Arya, Arman, Lara and Muriel!!!";
      String help = "Type 'help' to see the commands";
      String line = yellow + "________________________________________________________________" + white;

      // Cool Printable Message

      System.out.println();
      System.out.println();
      // line

      for (int i = 0; i < line.length(); i++) {

        System.out.printf("%c", line.charAt(i));
        Thread.sleep(5);
      }
      System.out.println();
      System.out.println();

      for (int i = 0; i < welcome.length(); i++) {

        System.out.printf("%c", welcome.charAt(i));
        Thread.sleep(5);
      }

      System.out.println();
      System.out.println();

      for (int i = 0; i < creators.length(); i++) {

        System.out.printf("%c", creators.charAt(i));
        Thread.sleep(5);
      }

      System.out.println();
      System.out.println();

      for (int i = 0; i < help.length(); i++) {

        System.out.printf("%c", help.charAt(i));
        Thread.sleep(5);
      }

      System.out.println();
      System.out.println();

      for (int i = 0; i < line.length(); i++) {

        System.out.printf("%c", line.charAt(i));
        Thread.sleep(5);
      }

      System.out.println();
      System.out.println();

      /**
       * Sorry for all the strings
       * I just wanted to have the "blood" text coloured in red
       */

      String firstScentence = "In front of you there is a large house with no lights on.";
      String secondScentence = "The windows are boarded up and you can hear squeaking and faint screaming.";
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

    else if (commandWord.equals("fred")) {
      System.out.println();
      System.out.println();

      String importantMessage = "Fred is hot";

      for (int i = 0; i < importantMessage.length(); i++) {

        System.out.printf("%c", importantMessage.charAt(i));
        Thread.sleep(500);

      }
      System.out.println();
      System.out.println();
    } else if (commandWord.equals("winson")) {

      int ran = (int) (Math.random() * 2);
      if (ran == 0) {
        String quotes = "''";

        System.out.println();
        System.out.print("Coincidence: ");
        System.out.println(quotes
            + "A situation in which events happen at the same in a way that is not planned or expected." + quotes);
        System.out.println();
        System.out.println("...however, I think math");
        System.out.println();
        System.out.println("    -Greg Winson 2011-2021");
      } else {
        System.out.println();
        System.out.println("Remember, the most common mistakes in Math is arithmetic involving negative numbers.");
        System.out.println();
        System.out.println("     -Greg Winson 2011-2021");
      }

      
      if(playerInventory.hasAllKeys()){
        System.out.println("Since you collected all three keys you now have access to the cellar - go to the next room and use the trapdoor");
        //gotta figure out how to unlock doors ask Mr. Deslauriers 
        /***************************************************************************************************
         * OVER HERE IS THE QUESTION
         * ***************************************************************************************************
         */
      } 
      

    }
    return false;
  }

  // implementations of user commands:

  private void attack(Command command) throws InterruptedException {
    if (currentRoom.hasEnemy()) {
      if (!command.hasExtraWords()) {
        System.out.println();
        System.out.println("I need more information");
        System.out.println();
      } else {
        String weaponName;
        if (command.getExtraWords().size() > 1) {
          String first = command.getExtraWords().get(0);
          String second = command.getExtraWords().get(1);
          weaponName = first + " " + second;
        } else {
          weaponName = command.getExtraWords().get(0);
        }
        if (validWeapon(weaponName) && playerInventory.inInventory(weaponName)) {
          Weapon weapon = (Weapon) playerInventory.findItem(weaponName); 

        
          currentRoom.getCharacter().setHealth(currentRoom.getCharacter().getHealth() - weapon.getDamage());
          playerHealth -= currentRoom.getCharacter().getDamage(); 
          System.out.println("You did " + weapon.getDamage() + " damage on " + currentRoom.getCharacter().getName() + " they did " + currentRoom.getCharacter().getDamage() + " damage to you, your health is now " + playerHealth + " and " + currentRoom.getCharacter().getName() + currentRoom.assessCharacterQuote());
          if(playerHealth <= 0){
            System.out.println();
            System.out.println(red + "YOU DIED, better luck next time..." + white);
            System.out.println("''Sometimes, the things you see in the shadows are more than just shadows.''");
            System.out.println();
            reset();
          }
          if(currentRoom.getCharacter().getHealth() <= 0){
            System.out.println();
            System.out.println("You have defeated " + currentRoom.getCharacter().getName());
            System.out.println();
            currentRoom.removeCharacter(); 
            String healthMessage = blue + "Your health has been restored to 500HP" + white;

              for(int i = 0; i < healthMessage.length(); i++){
                System.out.printf("%c", healthMessage.charAt(i));
                Thread.sleep(30);
              }
              System.out.println();
              System.out.println();

            playerHealth = 500;

          }  if(currentRoom.getCharacter().getName().equals("Shreck") && currentRoom.getRoomName().equals("Cellar")){

            System.out.println(green + currentRoom.getCharacter().getDescription() + white);

            }  else if(currentRoom.getRoomName().equals("Cellar") && currentRoom.getCharacter().getName() != "Shreck"){
              System.out.println(currentRoom.getCharacter().getDescription());

            }else{
              if(!currentRoom.getRoomName().equals("Cell")){
                System.out.println();
                System.out.println("You now receive key 3");
                Item key3 =  new Item(50, "key3", false, "Congratulations on finding the last key, but don't celebrate just yet. Head down to the cellar to figure out what's next."); 
                playerInventory.add(key3); 
              }
            }
          } 
         else {
          if(!validWeapon(weaponName)){
            System.out.println();
            System.out.println(weaponName + " is not a valid weapon.");
            System.out.println();
          }else{
            System.out.println();
            System.out.println(weaponName + " is not in your inventory.");
            System.out.println();
          }
        }
      }
    } else {
      System.out.println("There is nothing to attack/kill in this space.");
    }
  }

  private boolean validWeapon(String weaponName) {
    for (String s : weapons) {
      if (s.equals(weaponName)) {
        return true;
      }
    }
    return false;
  }

  private void read(Command command) {
    if(!command.hasExtraWords()){
      System.out.println("read what?");
    }else{
      String itemName;

      if (command.getExtraWords().size() > 1) {
        String first = command.getExtraWords().get(0);
        String second = command.getExtraWords().get(1);
        itemName = first + " " + second;
      } else {
        itemName = command.getExtraWords().get(0);
      }

      if (!playerInventory.inInventory(itemName)) {
        System.out.println();
        System.out.println("I can't read " + itemName + " because it is not in your inventory");
        System.out.println();
      }
      else{
        String description = playerInventory.readItem(itemName);
        System.out.println();
        System.out.println(description);
        System.out.println();
      }
    }
  }
  private void inventorySpace(Command command){
    playerInventory.inventorySpace();
  }

  private void search(Command command) throws InterruptedException {
    currentRoom.search();
  }
  
  /**
   * Print out some help information and a list of the command words.
   * 
   * @throws InterruptedException // this is for the printed message it doesnt do
   *                              anything
   */
/*  private void inspectTheItem(Command command){

    if(playerInventory.getCurrentWeight() == 0){
      System.out.println("You have no items");
    }else{
    
  playerInventory.inspectInventoryWeapon(itemName);
  }

}
*/

  private void printHelp() throws InterruptedException {
    String helperMessage = "Your command words are below, use them to win the game ";

    System.out.println();
    System.out.println();

    for (int i = 0; i < helperMessage.length(); i++) {

      System.out.printf("%c", helperMessage.charAt(i));

      Thread.sleep(15);
    }
    System.out.println();
    System.out.println();

    parser.showCommands();

    System.out.println();
    System.out.println();

  }
  public void displayInventory() throws InterruptedException {
    playerInventory.displayInventory();
  }

 
  private void takeItem(Command command) {

    if (!command.hasExtraWords()) {
      System.out.println();
      System.out.println("Take What?");
      System.out.println();
    } else {
      String itemName;
      if (command.getExtraWords().size() > 1) {
        String first = command.getExtraWords().get(0);
        String second = command.getExtraWords().get(1);
        itemName = first + " " + second;
      } else {
        itemName = command.getExtraWords().get(0);
      }

      Item item = currentRoom.removeItem(itemName);
      if (item == null) {
        System.out.println();
        System.out.print("This " + itemName + " does not exist in this room. ");
        System.out.println("Or try typing " + blue + "search " + white + "to find the proper name of the item");
      } else {
        if (!playerInventory.add(item)) 
          currentRoom.addItem(item);
        else{
          System.out.println();
          System.out.println("You took the " + itemName);
        }
      }
      System.out.print(currentRoom.exits());
      System.out.println();

    }
  }

  private void dropItem(Command command) {

    if (!command.hasExtraWords()) {
      System.out.println();
      System.out.println("Drop What?");
      System.out.println();
    } else {
      String itemName;
      if (command.getExtraWords().size() > 1) {
        String first = command.getExtraWords().get(0);
        String second = command.getExtraWords().get(1);
        itemName = first + " " + second;
      } else {
        itemName = command.getExtraWords().get(0);
      }

      Item item = playerInventory.remove(itemName);
      if (item == null) {
        System.out.println();
        System.out.println("You don't have " + itemName + " in your inventory");
        System.out.println();
      } else {
        currentRoom.addItem(item);
        System.out.println();
        System.out.println("You dropped the " + itemName);
      }
      System.out.println(currentRoom.exits());
      System.out.println();
    }
  }

  /**
   * Try to go to one direction. If there is an exit, enter the new room,
   * otherwise print an error message.
   */
  private void goRoom(Command command) {
    if (!command.hasExtraWords()) {
      // if there are no second word, we don't know where to go...
      System.out.println();
      System.out.println("Go where?");
      return;
    }

    ArrayList<String> rest = command.getExtraWords();

    String direction = rest.get(0);

    // Try to leave current room.
    Room nextRoom = currentRoom.nextRoom(direction);

    if (nextRoom == null) {
      System.out.println();
      System.out.println("Can't go there!");
    } else {
      currentRoom = nextRoom;
      System.out.println(currentRoom.longDescription());
    }
  }
}