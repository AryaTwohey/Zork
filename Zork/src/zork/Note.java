package zork;

public class Note extends Item{ 
  private int weight;
  private String name;
  private boolean isOpenable;
  private String description; 

  
  public Note(int weight, String name, boolean isOpenable, String description) {
    this.weight = weight;
    this.name = name;
    this.isOpenable = isOpenable;
    this.description = description; 
    
  }
  

  public Note() {
    
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

 public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
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




}


