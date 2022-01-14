package zork;

public class Character {
  private int health;
  private String name;
  private boolean isOpenable;
  private String description; 
  private String location; 
  private int damage; 

  
  public Character(int health, String name, boolean isOpenable, String location) {
    this.health = health;
    this.name = name;
    this.isOpenable = isOpenable;
    this.location = location;
    this.damage = damage; 
  }
  

  public Character() {
  }

/**
   * gets the health of each character
   */
  public int getHealth() {
    return health;
  }
  

  /**
   * sets the health of each character 
   */  
  public void setHealth(int health) {
    this.health = health;
  }
  

  /**
   * gets the description of each character
   */
  public String getDescription() {
    return description;
  }


   /**
   * sets the description of each character 
   */
  public void setDescription(String description) {
    this.description = description;
  }
 


  /**
   * sets the starting location of each character (which room the character is in)
   */
  public void setLocation(String location) {
    this.location = location;
  }
  
  
  /**
   * gets the starting location of each character (which room the character is in)
   */
  public String getLocation() {
    return location;
  }
  

  /**
   * gets the name of each character
   */
  public String getName() {
    return name;
  }


  /**
   * sets the name of each character
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * shows whether the character can be opened
   */
  public boolean isOpenable() {
    return isOpenable;
  }

  /**
   * sets whether the character is openable 
   */
  public void setOpenable(boolean isOpenable) {
    this.isOpenable = isOpenable;
  }


  /**
   * sets the damage that the character has on you
   */
  public void setDamage(int charDamage) {
    this.damage = charDamage; 
  }


  /**
   * gets the amount of damage that the character has
   */
  public int getDamage() {
    return damage; 
  }

}


