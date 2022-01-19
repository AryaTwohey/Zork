/**
 * Arman and Arya worked on inventory
 */
package zork;

import java.util.ArrayList;

public class Inventory {
  public static final String yellow = "\u001B[33m"; // for displayInventory
  public static final String white = "\u001B[37m"; // for displayInventory
  public static final String blue = "\u001B[34m"; // for another functionpublic static final String TEXT_RED = "\u001B[31m";
  public static final String red = "\u001B[31m";  //for "keep searching"


  private ArrayList<Item> items;
  private int maxWeight;

  public Inventory(int maxWeight) {
    this.items = new ArrayList<Item>();
    this.maxWeight = maxWeight;
    
  }

  public int getMaxWeight() {
    return maxWeight;
  }

  public int getCurrentWeight() {
    int currentWeight = 0;
    for(Item item: items){
      currentWeight+=item.getWeight();
    }

    return currentWeight;
  }

  /**
   * Arya did this, Arman helped with some parts of it
   * @param item the item that the player wishes to add to their inventory 
   * checks if the player has enough space to add the item to their inventory 
   * if they do it adds the item to the player's inventory and returns true 
   * if the item that the player wanted to pick up weighs more than the inventory max weight we know that this item is not able to be taken 
   * else we print that the player does not have enough space to pick up the item
   * @return true if successful, false if not successful in adding item to their inventory
   */
  public boolean add(Item item) {

    if (item.getWeight() + getCurrentWeight() <= maxWeight) {

      return items.add(item);

    } else if(item.getWeight() > maxWeight){
      System.out.println();
      System.out.println("This item is not able to be taken");
      System.out.println();
    } 
    else {
      System.out.println();
      System.out.println("You do not have enough space to take this item");
      System.out.println();
    }
    return false;
  }

  /**
   * Arya did this
   * displays the players inventory
   * @throws InterruptedException
   */
  public void displayInventory() throws InterruptedException {

    String message = blue + "Displaying Inventory" + white;
    System.out.println();
    System.out.println();

    if(items.size() == 0){
      System.out.println("Search for items around the map, and find them here");
      System.out.println();
    }else{


    for (int i = 0; i < message.length(); i++) {

      System.out.printf("%c", message.charAt(i));
      Thread.sleep(50);
    }
    System.out.println();
    System.out.println();

    for (Item i : items) {
      
      System.out.println(blue + "- " + white + i.getName() + " ");
    }
  }
}
  /**
   * Arya did this 
   * This method is used to see the description of items
   * That are too heavy to pick up, for example a TV or Fridge
   */
  public String inspectRoomItem(String itemName){
    for(Item i:items){  //iterate through arraylist<> of items
      if(i.getName().equals(itemName)){   //grab the name of each item and check if it matches itemName
        return itemName + ": " + i.getDescription(); //if the names match, return the description
      }
    }
    
    return "* Unable to inspect - either the item does not exist or is already in your inventory *";  //if the names do not match, then display message
       
  }    
  
  
    //Arya did this
 public void searchRoom() throws InterruptedException {

    String noItems = red + "Keep Searching..." + white; //printable message for when no items are found
    String youFound = blue + "You Found: " + white;     //printable message for when items are found

    if (items.size() == 0) {
      System.out.println();
      for (int i = 0; i < noItems.length(); i++) {    //prints "keep searching..." message when the rooms inventory.size() returns 0
        System.out.printf("%c", noItems.charAt(i));
        Thread.sleep(20);
      }
      System.out.println();
      System.out.println();

    } else {
      System.out.println();
      for (int i = 0; i < youFound.length(); i++) {   //Prints "you found" message, followed by the items
        System.out.printf("%c", youFound.charAt(i));
        Thread.sleep(20);
      }

      for (int i = 0; i < items.size(); i++) {    //uses to determine where the final item is to insert "and" text
        if (i == items.size() - 2) {
          System.out.print(items.get(i).getName() + " and ");
        } else if (i == items.size() - 1) { //if the items.size() returns 1, then no "and message occurs, and a single item is printed"
          System.out.print(items.get(i).getName());
        } else {
          System.out.print(items.get(i).getName() + ", ");  //"used to display commas after every item name, until the last item"
        }
      }

      System.out.println();
      System.out.println();

    }
  }

  //Arya did this Arman helped
  /**Key Method For ZORK, which allows for items to removed from a specific inventory
   */
  public Item remove(String itemName) {

    /** Each room has an arraylist<> of items
      * This method iterates through that list to find the item name that matches the variable passed in
      * In this case, the method is looking for a item with the same name as itemName  */
    
    for (int i = items.size() - 1; i >= 0; i--) {

      Item item = items.get(i);

      if (item.getName().equals(itemName)) {
        return items.remove(i);
      }
    }
    return null;
  }

  //Arman did this
  /**Allows the user to read the descriptions of notes */
  public String readItem(String itemName){
    for (Item item : items) {
      if (item.isNote() && item.getName().equals(itemName)) { //Has a checker to see if a item is a note using isNote(), while also checking to see if the name of that item is the same as itemname
        return item.getDescription();
      }
    }
    return("Unable to read this item");     //if the item is not a note, print a message
  }

  /**
   * Arman did this
   * checks if the player has all three keys in their inventory (using the inInventory method, which is explained below)
   * @return true if player has all keys 
   * @return false if the player does not have all keys 
   */
  public boolean hasAllKeys(){
    if(inInventory("key 1") && inInventory("key 2") && inInventory("key 3")){
      return true; 
    }else{
      return false; 
    }
  }

  /**
   * Arman did this
   * @param name the name of an item 
   * searches through player inventory using enhanced for loop
   * @return true if it finds that item
   * @return false if it doesn't find it 
   */
  public boolean inInventory(String name){
    for (Item i : items) {
      if(i.getName().equals(name)){
        return true; 
      }
    }
    return false; 
  }

  /**
   * Arman did this
   * searches through the items using an enhanced for loop
   * @param itemName takes in the name of an item
   * @return if it found an item with a name corresponding to its parameter it will return the item
   * Otherwise it return null
   */
  public Item findItem(String itemName) {
    for(Item i : items){
      if(i.getName().equals(itemName)){
        return i; 
      }
    }
    return null; 
  }

   /**
    * Arya did this
   * calculates the total inventory space that the user has left (not how much they have used)
   */
  public void inventorySpace(){
    
    int sum = 0;  //sum will be used to determine the total weight of all the items in your inventory
    int part = 0; //part will be used to determine the percentage of space left in playerInventory
    int total = 4317; //total inventory space passed in with constructor in game class

    if(items.size() == 0){  //If items.size() returns zero, you have 100% capacity left

      System.out.println();
      System.out.println(blue + "100% Carrying Capacity Left" + white);

    }else{

    for(Item i: items){    //If not, iterate through list of items and add each 

     int value = i.getWeight(); //value is each items weight

     sum += value;    //add the value of "value" sum
    }   
    part = total - sum;   //part = difference of whole to sum
    part = ((part * 100)) / total;    //final calculation to find percentage

    System.out.println();
    System.out.println("Total Inventory Space Left: " + blue + part +"%" + white);

  }
}
/**Basic toString method that every inventory has */

  public String toString() {
    String msg = "";

    for (Item item : items) {
      msg += item + ", ";
    }

    if (msg.length() > 0) {
      msg = msg.substring(msg.length() - 2);
    }
    return msg;
  }

}