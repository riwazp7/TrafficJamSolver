/* Vehicle.java
 * (c) RIWAZ POUDYAL 2016
 * Stores a vehicle which is a part of a state
 * A vehicle can either be a Truck (length 3) or a Car (length 2)
 * It can be top-down or left-right
 * Vehicles can move in either the A or the B direction
 * A direction is top for top-down vehicles and left for left-right vehicles.
 * So, B direction is down for top-down and right for left-right vehicles.
 */

public class Vehicle implements VehicleInterface {

  // Start is strictly either the leftmost (for left-right)
  // or the topmost (for top-down) coordinate
  protected Coor start;
  protected Coor end;
  protected final boolean isTopDown;
  protected final boolean isTruck;


  public Vehicle(Coor start, Coor end) {
    this.start = start;
    this.end = end;
    this.isTopDown = Vehicle.isTopDown(start, end);
    this.isTruck = Vehicle.isTruck(start, end);
  }

  public Vehicle(Pair<Coor, Coor> pair) {
    this.start = pair.x;
    this.end = pair.y;
    this.isTopDown = Vehicle.isTopDown(start, end);
    this.isTruck = Vehicle.isTruck(start, end);
  }

  // This constructor clones a given vehicle
  public Vehicle(Vehicle vehicle) {
    this.start = vehicle.getStart();
    this.end = vehicle.getEnd();
    this.isTopDown = vehicle.isTopDown();
    this.isTruck = vehicle.isTruck();
  }

  /*
   *  Getter methods
   */
  public Coor getStart() {
    return start;
  }

  public Coor getEnd() {
    return end;
  }

  public boolean isTruck() {
    return isTruck;
  }

  public boolean isTopDown() {
    return isTopDown;
  }

  /*
   *  Methods to check if a move is possible and change a vehicle's position
   *  Start and/or End coordinates modified.
   */
   public boolean canMoveA(State state) {
     if (isTopDown()) {
       return !state.isMarked(new Coor(getStart().getRow() - 1, getStart().getCol()));
     }
     return !state.isMarked(new Coor(getStart().getRow(), getStart().getCol() - 1));
   }

   public boolean canMoveB(State state) {
     if (isTopDown()) {
       return !state.isMarked(new Coor(getEnd().getRow() + 1, getEnd().getCol()));
     }
     return !state.isMarked(new Coor(getEnd().getRow(), getEnd().getCol() + 1));
   }

  public void moveA(State state) {
    if (isTopDown()) {
      // Move car top
      Coor newStart = new Coor(start.getRow() - 1 , start.getCol());
      state.unmarkCoor(end);
      state.markCoor(newStart);
      start = newStart;
      end = new Coor(end.getRow() - 1, end.getCol());
    } else {
      // Move left
      Coor newStart = new Coor(start.getRow(), start.getCol() - 1);
      state.unmarkCoor(end);
      state.markCoor(newStart);
      start = newStart;
      end = new Coor(end.getRow(), end.getCol() - 1);
    }
  }

  public void moveB(State state) {
    if (isTopDown()) {
      // Move down
      Coor newEnd = new Coor(end.getRow() + 1, end.getCol());
      state.markCoor(newEnd);
      state.unmarkCoor(start);
      start = new Coor(start.getRow() + 1, start.getCol());
      end = newEnd;
    } else {
      // Move right
      Coor newEnd = new Coor(end.getRow(), end.getCol() + 1);
      state.markCoor(newEnd);
      state.unmarkCoor(start);
      start = new Coor(start.getRow(), start.getCol() + 1);
      end = newEnd;
    }
  }

  public boolean equals(Object obj) {
    if (obj instanceof Vehicle) {
      Vehicle vehicle = (Vehicle) obj;
      return (start.equals(vehicle.getStart()) && end.equals(vehicle.getEnd()));
    }
    return false;
  }

  public String toString() {
    return start.toString() + " " + end.toString();
  }

  /*
   *  Static helper methods
   */
  public static boolean isTruck(Coor start, Coor end) {
    if (java.lang.Math.abs(start.getCol() - end.getCol()) == 2
    || java.lang.Math.abs(start.getRow() - end.getRow()) == 2) {
      return true;
    }
    return false;
  }

  public static boolean isTopDown(Coor start, Coor end) {
    if (start.getRow() != end.getRow()) {
     return true;
   }
    return false;
  }

}
