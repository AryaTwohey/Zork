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

 public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  

  




}


