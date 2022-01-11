package zork;

public class Key extends Item {
  private String keyId;

  public Key(String keyId, String keyName, int weight, String description) {
    super(weight, keyName, false, description);
    this.keyId = keyId;
  }

  public String getKeyId() {
    return keyId;
  }
}
