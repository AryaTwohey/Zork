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


  public boolean addItem(Command command, Item item) {

 /**The first if statement checks for any extra words to use in the method
  * The next if statement checks to see if your command matches the item in front of you
  * After this, the weight is checks to see if it meets the requirement and return true or false
  * Either adds or ignores it
  */

  if(!command.hasExtraWords()){

    System.out.println();
    System.out.println("Take what?");
    System.out.println();

  }else if(command.getExtraWords().toString() != item.getName()){   //not equal means doesnt exist

    System.out.println();
    System.out.println("This item does not exist");
    System.out.println();

    //both the name and weight need to work

  }else if(command.getExtraWords().toString().equals(item.getName()) && item.getWeight() + currentWeight <= maxWeight){
    return items.add(item);
     
    }else{
      //if the items weight plus the current weight is too high, then tell the user

      System.out.println();
      System.out.println("There is no room to add the item, please drop something.");
      System.out.println();
    }
  return false;
  }

  public boolean dropItem(Command command) {

    if(command.getExtraWords().size() > 0){

     for (int i = items.size()-1; i >= 0; i--) {

      String itemName = command.getExtraWords().toString();

      System.out.println("MADE IT HERE");   //test to see if loop is working
    
      if (items.get(i).getName().equals(itemName)) {

        items.remove(i);

        
        return true;
        
      }else if(items.get(i).getName() != itemName){
        
        System.out.println();
        System.out.println("You do not have this item.");
        System.out.println();
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
