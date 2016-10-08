/* VehicleInterface.java
 * (c) RIWAZ POUDYAL 2016
 */
public interface VehicleInterface {
  // return if a vehicle is a Truck
  public boolean isTruck();

  // return true if a vehicle is top-down
  // false if it is left-right
  public boolean isTopDown();

  public Coor getStart();

  public Coor getEnd();

  // Assume direction A is up for up-down vehicles
  //and right for right-left vehicles
  public boolean canMoveA(State state);
  public boolean canMoveB(State state);
  void moveA(State state);
  void moveB(State state);
}
