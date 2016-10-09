/* RedCar.java
 * Special Red Car in the puzzle. Has the heuristic and goal test function.
 * (c) RIWAZ POUDYAL 2016
 */
public final class RedCar extends Vehicle implements RedCarInterface {

  public RedCar(Pair<Coor, Coor> pair) {
    super(pair);
  }

  // constructor to clone a RedCar object
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

  /*
   * This version of the method modifies the search to stop only when the
   * red car is at the exit.
   */
  // public boolean canExit(State state) {
  //   return start.getRow() == 0;
  // }

  /*
   * heuristic function 3
   */
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

  /*
   * heuristic function 2
   */
  // public int getHeuristicValue(State state) {
  //   return start.getRow();
  // }

  /*
   * heuristic function 1
   */
  // public int getHeuristicValue(State state) {
  //   return 0;
  // }
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof RedCar) {
      RedCar vehicle = (RedCar) obj;
      return (start.equals(vehicle.getStart()) && end.equals(vehicle.getEnd()));
    }
    return false;
  }
}
