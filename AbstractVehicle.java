public abstract class AbstractVehicle implements Vehicle {

  // Does it make sense to have set methods for the coordinates of a vehicle?
  // Umm ...no?

  // Start is strictly either the leftmost (for left-right) or the top most coordinate
  protected Coor start;
  protected Coor end;
  protected boolean isTopDown;

  public AbstractVehicle(Coor start, Coor end) {
    this.start = start;
    this.end = end;
    if (start.getRow() != end.getRow()) isTopDown = true;
    //Set is Truck method please. Need a boolean? Yes good idea.
    //And is top down
  }

  public Coor getStart() {
    return start;
  }

  public Coor getEnd() {
    return end;
  }

  public boolean isTruck() {
    return isTruck(this);
  }

  public boolean isTopDown() {
    return isTopDown;
  }

  public void moveA(State state) {
    if (!canMoveA(state)) {
      throw new RuntimeException("Can't move towards direction A");
    }
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
    if (!canMoveB(state)) {
      throw new RuntimeException("Can't move towards direction B");
    }
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

  // Handle out of board requests here instead?
  public boolean canMoveA(State state) {
    if (isTopDown()) {
      return !state.isMarked(new Coor(getStart().getRow() - 1, getStart().getCol()));
    }
    return !state.isMarked(new Coor(getStart().getRow(), getStart().getCol() - 1));
  }

  // Handle out of board requests?
  public boolean canMoveB(State state) {
    if (isTopDown()) {
      return !state.isMarked(new Coor(getEnd().getRow() + 1, getEnd().getCol()));
    }
    return !state.isMarked(new Coor(getEnd().getRow(), getEnd().getCol() + 1));
  }

  public static boolean isTruck(Pair<Coor, Coor> pair) {
    Coor start = pair.x;
    Coor end = pair.y;
    if (java.lang.Math.abs(start.getCol() - end.getCol()) == 2
    || java.lang.Math.abs(start.getRow() - end.getRow()) == 2) {
      return true;
    }
    return false;
  }

  public static boolean isTruck(Vehicle vehicle) {
    return isTruck(new Pair<Coor, Coor>(vehicle.getStart(), vehicle.getEnd()));
  }

}
