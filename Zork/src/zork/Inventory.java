package zork;

import java.util.ArrayList;

public class Inventory {
  public static final String yellow = "\u001B[33m"; // for displayInventory
  public static final String white = "\u001B[37m"; // for displayInventory
  public static final String blue = "\u001B[34m"; // for another functionpublic static final String TEXT_RED = "\u001B[31m";
  public static final String red = "\u001B[31m";  //for "keep searching"


  private ArrayList<Item> items;
  private int maxWeight;
  private int currentWeight;

  public Inventory(int maxWeight) {
    this.items = new ArrayList<Item>();
    this.maxWeight = maxWeight;
    this.currentWeight = 0;
  }

  public int getMaxWeight() {
    return maxWeight;
  }

  public int getCurrentWeight() {
    return currentWeight;
  }

  public boolean add(Item item) {

    if (item.getWeight() + currentWeight <= maxWeight) {
      return items.add(item);

    } else {
      System.out.println();
      System.out.println("There is no room to add this item");
      System.out.println();
    }
    return false;
  }

  // not needed but keeping for now
  public boolean hasItem() {

    if (items.size() == 0) {
      System.out.println();
      System.out.println("This room has no items");
      System.out.println();
    }
    for (int i = 0; i < items.size(); i++) {
      System.out.println(i + " ");
    }
    return true;
  }

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

  public void searchRoom() throws InterruptedException {

    String noItems = red + "Keep Searching..." + white;
    String youFound = blue + "You Found: " + white;

    if (items.size() == 0) {
      System.out.println();
      for (int i = 0; i < noItems.length(); i++) {
        System.out.printf("%c", noItems.charAt(i));
        Thread.sleep(20);
      }
      System.out.println();
      System.out.println();

    } else {
      System.out.println();
      for (int i = 0; i < youFound.length(); i++) {
        System.out.printf("%c", youFound.charAt(i));
        Thread.sleep(20);
      }

      for (int i = 0; i < items.size(); i++) {
        if (i == items.size() - 2) {
          System.out.print(items.get(i).getName() + " and ");
        } else if (i == items.size() - 1) {
          System.out.print(items.get(i).getName());
        } else {
          System.out.print(items.get(i).getName() + ", ");
        }
      }
      System.out.println();
      System.out.println();

    }
  }

  public Item remove(String itemName) {

    for (int i = items.size() - 1; i >= 0; i--) {

      Item item = items.get(i);

      if (item.getName().equals(itemName)) {
        return items.remove(i);
      }
    }
    return null;
  }

  public String readItem(String itemName){
    for (Item item : items) {
      if (item.isNote() && item.getName().equals(itemName)) {
        return item.getDescription();
      }
    }
    return("Unable to read this item"); 
  }

  public boolean hasAllKeys(){
    if(inInventory("key 1") && inInventory("key 2") && inInventory("key 3")){
      return true; 
    }else{
      return false; 
    }
  }

  public boolean inInventory(String name){
    for (Item i : items) {
      if(i.getName().equals(name)){
        return true; 
      }
    }
    return false; 
  }

  public Item findItem(String itemName) {
    for(Item i : items){
      if(i.getName().equals(itemName)){
        return i; 
      }
    }
    return null; 
  }

  public void inventorySpace(){
    
 /* * * * * * * * * * * * * * * * * * * * *
  * Calculates the total inventory space  *
  * The user has LEFT not how much space  *
  * They have USED                        *
  * * * * * * * * * * * * * * * * * * * * */ 
    
    int sum = 0;
    int part = 0;
    int total = 6475;

    if(items.size() == 0){

      System.out.println();
      System.out.println(blue + "100% Carrying Capacity Left" + white);

    }else{

    for(int i = 0; i < items.size(); i++){

     int value = items.get(i).getWeight();

     sum += value;
    }
    part = total - sum;
    part = ((part * 100)) / total;

    System.out.println();
    System.out.println("Total Inventory Space Left: " + blue + part +"%" + white);

  }
}


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
