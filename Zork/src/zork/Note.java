package zork;

public class Note extends Item{ 
  private String description; 

  
  public Note(int weight, String name, boolean isOpenable, String description) {
    super(weight, name, isOpenable, description); 
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


