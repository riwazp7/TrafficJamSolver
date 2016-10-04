public class Car extends AbstractVehicle {
  public Car(Pair<Coor, Coor> pair) {
    this.start = pair.x;
    this.end = pair.y;
  }

  @Override
  public boolean canMoveA() {

  }

  @Override
  public boolean canMoveB() {

  }

  @Override
  public Coor moveA() {
    if (!canMoveA()) {
      throw new Exception("Invalid move direction A");
    }
  }

  @Override
  public Coor moveB() {
    if (!canMoveB()) {
      throw new Exception("Invalid move direction B");
    }
  }
}
