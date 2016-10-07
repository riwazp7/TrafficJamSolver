public class RedCar extends AbstractVehicle {

  public RedCar(Pair<Coor, Coor> pair) {
    super(pair.x, pair.y);
  }

  // Assuming the red car is always top down
  public boolean canExit(State state) {
    Coor exit = state.getExit();
    Coor currCoor = start;
    while(true) {
      if (currCoor.getRow() == exit.getRow()) {
        return true;
      } else if (state.isMarked(new Coor(currCoor.getRow() -1 , currCoor.getCol()))) {
        return false;
      } else {
        currCoor = new Coor(currCoor.getRow() - 1, currCoor.getCol());
      }
    }
  }
}
