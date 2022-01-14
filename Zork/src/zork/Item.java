package zork;

public class Item extends OpenableObject {
  private int weight;
  private String name;
  private boolean isOpenable;
  private String description;
  private boolean isNote;  

  /**
   * one of the constructors of item
   */
  public Item(int weight, String name, boolean isOpenable, String description) {
    this.weight = weight;
    this.name = name;
    this.isOpenable = isOpenable;
    this.description = description; 
  }
  

  /**
   * another constructor for item 
   */
  public Item() {
  }


  public void open() {
    if (!isOpenable)
      System.out.println("The " + name + " cannot be opened.");

  }

   /**
   * sets whether the item is a note or not 
   */
  public void setIsNote(boolean isNote){
    this.isNote = isNote; 
  }

   /**
   * gets the weight of each item
   */
  public int getWeight() {
    return weight;
  }

   /**
   * shows whether the item is a note or not
   */
  public boolean isNote(){
    return isNote; 
  }

   /**
   * sets the weight of each item
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }


   /**
   * gets the description of each item
   */
  public String getDescription() {
    return description;
  }

   /**
   * sets the description of each item
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
   /**
   * gets the name of each item
   */
  public String getName() {
    return name;
  }

  /**
   * sets the name of each item
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * shows whether the item can be opened or not 
   */
  public boolean isOpenable() {
    return isOpenable;
  }

  /**
   * sets whether the item can be opened or not
   */
  public void setOpenable(boolean isOpenable) {
    this.isOpenable = isOpenable;
  }
}
