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


  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return location;
  }
  
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


  public void setDamage(int charDamage) {
    this.damage = charDamage; 
  }

  public int getDamage() {
    return damage; 
  }

}


