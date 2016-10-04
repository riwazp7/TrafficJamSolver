public class State {

  int[][] board;
  ArrayList<Pair> carPositions;
  public State(int row, int col, ArrayList<Pair> carPositions) {
    this.carPositions = carPositions;
    board = new int[row][col];
    markBoard(carPositions);
  }

  void markBoard(ArrayList<Pair> carPositions) {
    for (Pair pair : carPositions.iterator()) {
      
    }
  }

}
