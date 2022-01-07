package zork;

public class Character {
  private int health;
  private String name;
  private boolean isOpenable;
  //private String description; 

  
  public Character(int health, String name, boolean isOpenable) {
    this.health = health;
    this.name = name;
    this.isOpenable = isOpenable;
    
  }
  

  public Character() {
  }


  public void open() {
    if (!isOpenable)
      System.out.println("The " + name + " cannot be opened.");

  }


  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

 /* public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isOpenable() {
    return isOpenable;
  }

  public void setOpenable(boolean isOpenable) {
    this.isOpenable = isOpenable;
  }


  public void setDescription(String itemDescription) {
  }

}


