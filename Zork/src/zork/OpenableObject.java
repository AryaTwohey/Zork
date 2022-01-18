package zork;

public class OpenableObject {
  private Boolean isLocked;
  private String keyId;
  private Boolean isOpen;

  //constructor for OpenableObject (no parameters)
  public OpenableObject() {
    this.isLocked = false;
    this.keyId = null;
    this.isOpen = false;
  }

  //constructor for OpenableObject (given isLocked, keyId, isOpen as parameters)
  public OpenableObject(boolean isLocked, String keyId, Boolean isOpen) {
    this.isLocked = isLocked;
    this.keyId = keyId;
    this.isOpen = isOpen;
  }

  //constructor for OpenableObject (given isLocked and keyId as parameters)
  public OpenableObject(boolean isLocked, String keyId) {
    this.isLocked = isLocked;
    this.keyId = keyId;
    this.isOpen = false;
  }

  //returns boolean isLocked
  public boolean isLocked() {
    return isLocked;
  }

  //takes in a boolean to set isLocked 
  public void setLocked(boolean isLocked) {
    this.isLocked = isLocked;
  }

  //returns the keyId
  public String getKeyId() {
    return keyId;
  }

  //returns isOpen
  public boolean isOpen() {
    return isOpen;
  }

  //takes in boolean to set isOpen
  public void setOpen(boolean isOpen) {
    this.isOpen = isOpen;
  }
}
