package zork;

public class Key extends Item {
  private String keyId;

  /**
   * constructor for the key (which is a type of item)
   */
  public Key(String keyId, String keyName, int weight, String description) {
    super(weight, keyName, false, description);
    this.keyId = keyId;
  }


  /**
   * gets the key's ID 
   */
  public String getKeyId() {
    return keyId;
  }
}
