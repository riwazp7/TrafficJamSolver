/* RedCar.java
 * (c) RIWAZ POUDYAL 2016
 */
public class RedCar extends Vehicle implements RedCarInterface {

  public RedCar(Pair<Coor, Coor> pair) {
    super(pair);
    confirmTopDown();
  }

  public RedCar(RedCar redCar) {
    super((Vehicle) redCar);
    confirmTopDown();
  }

  private void confirmTopDown() {
    if (!isTopDown()) {
      throw new RuntimeException("RedCar cannot be left-right");
    }
  }

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

  public Integer getHeuristicValue() {
    return 0;
  }
}
