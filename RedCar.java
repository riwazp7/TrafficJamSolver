public class RedCar extends AbstractVehicle {


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

  //
  public boolean canExit() {
    
  }
}
