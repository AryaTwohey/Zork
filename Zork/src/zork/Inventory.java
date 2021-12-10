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
    if (item.getWeight() + currentWeight <= maxWeight)
      return items.add(item);
    else {
      System.out.println("There is no room to add the item, please drop something.");
      return false;
    }
  }

  public boolean dropItem(Command command) {

    if(!command.hasSecondWord()){

      System.out.println();
      System.out.println("Drop What?");
      
    }else{

  String itemName = command.getSecondWord();
      
    for (int i = 0; i < items.size(); i++) {

      if (itemName.equals(items.get(i).getName())) {
        items.remove(i);
        return true;
      }
    }
  }
  return false;

}

  public String toString(){
    String msg = "";

    for (Item item : items){
      msg += item + ", ";
    }

    if (msg.length()>0){
      msg = msg.substring(msg.length()-2);
    }
    return msg;
  }
}
