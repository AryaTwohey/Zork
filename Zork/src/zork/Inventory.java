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

  public boolean addItem(Item item) {

    if (item.getWeight() + currentWeight <= maxWeight) {
      return items.add(item);

    } else {
      System.out.println();
      System.out.println("There is no room to add this item");
      System.out.println();
    }
    return false;
  }

  public Item dropItem(String Itemname) {

    for (int i = items.size() - 1; i >= 0; i--) {

      Item item = items.get(i);

      if (item.getName().equals(Itemname)) {
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
