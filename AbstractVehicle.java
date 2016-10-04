public abstract class AbstractVehicle implements Vehicle {
  private Coor start;
  private Coor end;

  public Coor getStart() {
    return start;
  }

  public Coor getEnd() {
    return end;
  }

  public boolean isTruck() {
    return isTruck(this);
  }

  abstract void moveA(State state);

  abstract void moveB(State state);

  abstract boolean canMoveA(State state);

  abstract boolean canMoveB(State state);

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
