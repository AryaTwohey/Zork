package zork;

public class Note extends Item{ 
  private int weight;
  private String name;
  private boolean isOpenable;
  private String description; 

  
  public Note(int weight, String name, boolean isOpenable, String description) {
    super(weight, name, isOpenable); 
    this.description = description; 
    
  }
  

  public Note() {

  }

 public String displayDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  

  




}


