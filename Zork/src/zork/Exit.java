//Lara and Muriel
package zork;

/**
 * Exit
 */
public class Exit extends OpenableObject {
  private String direction;
  private String adjacentRoom;

  /**
   * constructor for the exits 
   */
  public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId) {
    super(isLocked, keyId);
    this.direction = direction;
    this.adjacentRoom = adjacentRoom;
  }

  /**
   * another constructor for the exits 
   */
  public Exit(String direction, String adjacentRoom, boolean isLocked, String keyId, Boolean isOpen) {
    super(isLocked, keyId, isOpen);
    this.direction = direction;
    this.adjacentRoom = adjacentRoom;
  }

  /**
   * another constructor for the exits 
   */

  public Exit(String direction, String adjacentRoom) {
    this.direction = direction;
    this.adjacentRoom = adjacentRoom;
  }

  /**
   * gets the direction of exit 
   */
  public String getDirection() {
    return direction;
  }

  /**
   * sets the direction of exit 
   */
  public void setDirection(String direction) {
    this.direction = direction;
  }

  /**
   * gets the adjacent room (that you exit into)
   */
  public String getAdjacentRoom() {
    return adjacentRoom;
  }

  /**
   * sets the adjacent room 
   */
  public void setAdjacentRoom(String adjacentRoom) {
    this.adjacentRoom = adjacentRoom;
  }

}