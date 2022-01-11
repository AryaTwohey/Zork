package zork;

public class Item extends OpenableObject {
  private int weight;
  private String name;
  private boolean isOpenable;
  private String weaponDamage;
  private String description; 

  
  public Item(int weight, String name, boolean isOpenable) {
    this.weight = weight;
    this.name = name;
    this.isOpenable = isOpenable;
   
    
  }
  

  public Item() {
  }


  public void open() {
    if (!isOpenable)
      System.out.println("The " + name + " cannot be opened.");

  }


  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
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
    this.description = itemDescription;
  }

  public void setDamage(String WeaponDamage){
    this.weaponDamage = WeaponDamage;
  }

}
