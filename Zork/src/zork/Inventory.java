package zork;

import java.util.ArrayList;

public class Inventory {

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

  public boolean hasItem(Command command){
  if(items.size() == 0){
    System.out.println();
    System.out.println("This room has no items");
    System.out.println();
  }
  for(int i = 0; i < items.size(); i++){
   System.out.println(i + " ");

  }
  return true;

  }
public void searchRoom(){
  
if(items.size() == 0){

  System.out.println();
  System.out.println("This room has no items");
  System.out.println();
}else{
    System.out.println();
    System.out.print("You found a: ");

    for(int i = 0; i < items.size(); i++){
    System.out.print(items.get(i).getName() + "  ");
    }
    System.out.println();
  }
}
  public Item remove(String itemname) {

    for (int i = items.size() - 1; i >= 0; i--) {

      Item item = items.get(i);

      if (item.getName().equals(itemname)) {
        return items.remove(i);
      }
    }
    return null;
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
