import java.util.ArrayList;

public class State {

  // Tracks empty coordinates on the board
  boolean[][] board;

  // List of all the cars in the board. Also contains the red car!
  ArrayList<Vehicle> carList;

  // The red car
  Vehicle redCar;

  // The coordinate the red car must exit through.
  Coor exit;

  // Default max no of rows
  private int row = 6;

  // Default max no of cols
  private int col = 6;

  public State(int row, int col, ArrayList<Pair<Coor, Coor>> positions, Pair<Coor, Coor> redCarPos, Coor exit) {
    this.row = row;
    this.col = col;
    this.exit = exit;
    carList = new ArrayList<Vehicle>();
    board = new boolean[row][col];
    for (Pair<Coor, Coor> pair : positions) {
      if (AbstractVehicle.isTruck(pair)) {
        carList.add(new Truck(pair));
      } else {
        carList.add(new Car(pair));
      }
    }
    this.redCar = new RedCar(redCarPos);
    carList.add(redCar);
    markBoard();
  }

  private void markBoard() {
    for (Vehicle vehicle : carList) {
      Coor start = vehicle.getStart();
      Coor end = vehicle.getEnd();
      markCoor(start);
      markCoor(end);
      if (vehicle.isTruck()) {
        Coor midCoor;
        if (vehicle.isTopDown()) {
          midCoor = new Coor((start.getRow() + end.getRow()) / 2, start.getCol());
        } else {
          midCoor = new Coor(start.getRow(), (start.getCol()+ end.getCol()) / 2);
        }
        markCoor(midCoor);
      }
    }
  }

  // Use carefully
  public void markCoor(Coor coor) {
    board[coor.getRow()][coor.getCol()] = true;
  }

  public void unmarkCoor(Coor coor) {
    board[coor.getRow()][coor.getCol()] = false;
  }

  // Handle out of board coordinates?
  public boolean isMarked(Coor coor) {
    if (coor.getRow() >= row || coor.getCol() >= col) {
      return true;
    }
    return board[coor.getRow()][coor.getCol()];
  }

  public Coor getExit() {
    return exit;
  }

  public static void main(String[] args) {
    ArrayList<Pair<Coor, Coor>> vehicles = new ArrayList<Pair<Coor, Coor>>();

  }
}
