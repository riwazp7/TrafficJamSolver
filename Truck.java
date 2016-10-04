public class Truck extends AbstractVehicle {

  public Truck(Pair<Coor, Coor> pair) {
    this.start = pair.x;
    this.end = pair.y;
  }

  @Override
  public boolean canMoveA() {

  }

  @Override
  public boolean canMoveB() {

  }

  @Override boolean moveA() {
    if (!canMoveA()) {
      throw new Exception("Invalid move direction A");
    }
  }

  @Override
  public boolean moveB() {
    if (!canMoveB()) {
      throw new Exception("Invalid move direction B");
    }
  }
}
