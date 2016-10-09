/* RedCar.java
 * (c) RIWAZ POUDYAL 2016
 */
public class RedCar extends Vehicle implements RedCarInterface {

  public RedCar(Pair<Coor, Coor> pair) {
    super(pair);
  }

  public RedCar(RedCar redCar) {
    super((Vehicle) redCar);
  }

  public boolean canExit(State state) {
    int exitCol = state.getExit().getCol();
    int redCarRow = start.getRow() - 1;
    boolean[][] board = state.getBoard();
    while (redCarRow >= 0) {
      if (board[redCarRow][exitCol]) {
        return false;
      }
      redCarRow--;
    }
    return true;
  }

  public int getHeuristicValue(State state) {
    int exitCol = state.getExit().getCol();
    int redCarRow = start.getRow() - 1;
    int value = 0;
    boolean[][] board = state.getBoard();
    while (redCarRow >= 0) {
      if (board[redCarRow][exitCol]) {
        value++;
      }
      redCarRow--;
    }
    return value;
  }

  public boolean equals(Object obj) {
    if (obj instanceof RedCar) {
      RedCar vehicle = (RedCar) obj;
      return (start.equals(vehicle.getStart()) && end.equals(vehicle.getEnd()));
    }
    return false;
  }
}
