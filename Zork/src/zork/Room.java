package zork;

import java.util.ArrayList;

public class Room {

  private String roomName;
  private String description; 
  private ArrayList<Character> characters;  
  private ArrayList<Exit> exits;
  Inventory inventory;

  public ArrayList<Exit> getExits() {
    return exits;
  }

  public void setExits(ArrayList<Exit> exits) {
    this.exits = exits;
  }

  /**
   * Create a room described "description". Initially, it has no exits.
   * "description" is something like "a kitchen" or "an open court yard".
   */
  public Room(String description, boolean isLocked) {
    this.description = description;
    exits = new ArrayList<Exit>();
    inventory = new Inventory(Integer.MAX_VALUE);
  }

  public Room() {
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
    return "Room: " + roomName + "\n\n" + description;
  }

  /**
   * Return a long description of this room, on the form: You are in the kitchen.
   * Exits: north west
   */
  public String longDescription() {

    return "Room: " + roomName + "\n\n" + description + "\n" + exitString();
  }

  /**
   * Return a string describing the room's exits, for example "Exits: north west
   * ".
   */
  private String exitString() {
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
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  //not needed
  /*public boolean hasItem() {
    return  inventory.hasItem();
  }

  */
  public String exits() {

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
    return inventory.remove(itemName);
  }

  public boolean addItem(Item item) {
    return inventory.add(item);
  }

  public boolean hasEnemy(){
    return characters.size() > 0;  
  }




  public Character getCharacter(){
    if(characters.size()>0){
      return characters.get(0); 

    }else{
      return null;
    }
  }

  
  public void removeCharacter() {

    characters.remove(0); 
  }

  public boolean noMoreEnemies(){
    if(characters.size() == 0){
      return true; 
    }
    return false; 
  }

  public void addCharacter(Character character) {
    characters.add(character); 
  }

  public String assessCharacterQuote() {
    return ""; 
  }
}
