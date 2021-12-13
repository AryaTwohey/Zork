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

    if(command.getExtraWords().toString().length() != 0){

     for (int i = items.size()-1; i >= 0; i--) {

      String itemName = command.getExtraWords().toString();

      System.out.println("MADE IT HERE");   //test to see if loop is working
    
      if (items.get(i).getName().equals(itemName)) {

        items.remove(i);

        return true;

      }
    }
  }else{
    System.out.println();

    System.out.println("Drop What ");
    
    System.out.println();
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
