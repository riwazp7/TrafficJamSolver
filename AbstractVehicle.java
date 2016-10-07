public abstract class AbstractVehicle implements Vehicle {
  private Coor start;
  private Coor end;
  private boolean isTopDown;

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

  void moveA(State state) {
    if (!canMoveA) {
      throw new Exception("Can't move towards direction A");
    }
    if (isTopDown()) {
      // Also update start and end
      state.unmarkCoor();
      state.markCoor();
    }
  }

  void moveB(State state) {

  }

  abstract void moveB(State state);

  boolean canMoveA(State state) {
    if (isTopDown()) {
      return state.isMarked(new Coor(getStart().getRow() + 1, getStart().getCol()));
    }
    return state.isMarked(new Coor(getStart().getRow(), getStart().getCol() + 1));
  }

  boolean canMoveB(State state) {
    if (isTopDown()) {
      return state.isMarked(new Coor(getEnd().getRow() - 1, getEnd().getCol()));
    }
    return state.isMarked(new Coor(getEnd().getRow(), getEnd().getCol() - 1));
  }

  // Does it make sense to have set methods for the coordinates?

  public static boolean isTruck(Pair<Coor, Coor> pair) {
    Coor start = pair.x;
    Coor end = pair.y;
    if (java.lang.Math.abs(start.getCol() - end.getCol()) == 3
    || java.lang.Math.abs(start.getRow() - end.getRow())) == 3 {
      return true;
    }
    return false;
  }

  public static boolean isTruck(Vehicle vehicle) {
    return isTruck(new Pair<Coor, Coor>(vehicle.getStart(), vehicle.getEnd()));
  }

}
