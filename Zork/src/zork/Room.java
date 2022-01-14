package zork;

import java.util.ArrayList;

public class Room {

  private String roomName;
  private String description; 
  private ArrayList<Character> characters;  
  private ArrayList<Exit> exits;
  Inventory inventory;

  public ArrayList<Exit> getExits() {
    /** 
     * returns the exists of that specific room
     */
    return exits;
  }

  public void setExits(ArrayList<Exit> exits) {
    this.exits = exits;
  }

  
  public Room(String description, boolean isLocked) {
    /** 
     * constructors of the room
     * gives the exits and the inventory 
     */
    this.description = description;
    exits = new ArrayList<Exit>();
    inventory = new Inventory(Integer.MAX_VALUE);
  }

  public Room() {
    /**
     * makes new array lists for description and characters
     */
    roomName = "DEFAULT ROOM";
    description = "DEFAULT DESCRIPTION";
    exits = new ArrayList<Exit>();
    inventory = new Inventory(Integer.MAX_VALUE);
    characters = new ArrayList<Character>(); 
  }

  public void addExit(Exit exit) throws Exception {
    exits.add(exit);
  }

  /**
   * Return the description of the room (the one that was defined in the
   * constructor).
   */
  public String shortDescription() {
    /** 
     * returns the name and the description for that room
     */
    return "Room: " + roomName + "\n\n" + description;
  }

  public String longDescription() {
    /** gives the room name, the description and the exists that room has */

    return "Room: " + roomName + "\n\n" + description + "\n" + exitString();
  }

  /**
   * Return a string describing the room's exits, for example "Exits: north west
   * ".
   */
  private String exitString() {
    /**
     * writing out the possible exists for that specific room 
     */
    String returnString = "Exits: ";
    for (Exit exit : exits) {
      returnString += exit.getDirection() + " ";
    }

    return returnString;
  }

  /**
   * Return the room that is reached if we go from this room in direction
   * "direction". If there is no room in that direction, return null.
   */
  public Room nextRoom(String direction, boolean hasAllKeys) {
    /**
     * finds out if the player has the 3 keys to open the door for the room
     * if not, then it tells them that the room is locked 
     * if they do, then it says that they can enter
     * sends them to the next room on the room map 
     */
    try {
      for (Exit exit : exits) {

        if (exit.getDirection().equalsIgnoreCase(direction)) {
          if(exit.isLocked() && !hasAllKeys){
            System.out.println();
            System.out.println("The door is locked.");
            System.out.println();
            return null; 
          }else if(hasAllKeys && exit.isLocked()) {
            System.out.println("Since you collected all three keys you now have access to all the rooms");
          }
          String adjacentRoom = exit.getAdjacentRoom(); 

          return Game.roomMap.get(adjacentRoom);
        }

      }
    } catch (IllegalArgumentException ex) {
      System.out.println(direction + " is not a valid direction.");
      return null;
    }
    System.out.println();
    System.out.println(direction + " is not a valid direction.");
    return null;
  }

  /*
   * private int getDirectionIndex(String direction) { int dirIndex = 0; for
   * (String dir : directions) { if (dir.equals(direction)) return dirIndex; else
   * dirIndex++; }
   * 
   * throw new IllegalArgumentException("Invalid Direction"); }
   */
  public String getRoomName() {
    /**
     * gives the room name
     */
    return roomName;
  }

  public void setRoomName(String roomName) {
    /** 
     * constructors to set the room name
     */
    this.roomName = roomName;
  }

  public String getDescription() {
    /**
     * returns the description of that specific room
     */
    return description;
  }

  public void setDescription(String description) {
    /**
     * using constructors to set the specific descriptions
     */
    this.description = description;
  }

  //not needed
  /*public boolean hasItem() {
    return  inventory.hasItem();
  }

  */
  public String exits() {
    /**
     * shows which directions that you can exit a room from
     */

    String returnString = "Exits: ";
    for (Exit exit : exits) {
      returnString += exit.getDirection() + " ";
    }
    return returnString;
  }

 public void search() throws InterruptedException{

  inventory.searchRoom();
 }

  public Item removeItem(String itemName) {
    /**
     * removes item from the inventory 
     */
    return inventory.remove(itemName);
  }

  public boolean addItem(Item item) {
    /**
     * adds items to the inventory 
     */
    return inventory.add(item);
  }

  public boolean hasEnemy(){
    /** 
     * determines if this character has an enemy
     */
    return characters.size() > 0;  
  }




  public Character getCharacter(){
    /**
     * orders the characters in the game 
     */
    if(characters.size()>0){
      return characters.get(0); 

    }else{
      return null;
    }
  }

  
  public void removeCharacter() {
    /**
     * removes the character within the game
     */

    characters.remove(0); 
  }

  public boolean noMoreEnemies(){
    /**
     * determines if the amount of characters is equal to zero, there are no more enemies to fight
     */
    if(characters.size() == 0){
      return true; 
    }
    return false; 
  }

  public void addCharacter(Character character) {
    /**
     * add characters into the game 
     */
    characters.add(character); 
  }

  public String assessCharacterQuote() {
    /**
     * return the quote for the character
     */
    return ""; 
  }
}
