import java.util.ArrayList;

public class State {

  private final static int DEFAULT_ROW = 6;
  private final static int DEFAULT_COL = 6;

  // Tracks empty coordinates on the board
  boolean[][] board;

  // List of all the cars in the board. Also contains the red car!
  ArrayList<Vehicle> carList;

  // The red car
  RedCar redCar;

  // The coordinate the red car must exit through.
  Coor exit;

  // Default max no of rows
  private int row;

  // Default max no of cols
  private int col;

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

  public State(ArrayList<Vehicle> carList, RedCar redCar, Coor exit) {
    this.carList = carList;
    this.redCar = redCar;
    this.exit = exit;
  }

  public State(ArrayList<Pair<Coor, Coor>> positions, Pair<Coor, Coor> redCarPos, Coor exit) {
    this(DEFAULT_ROW, DEFAULT_COL, positions, redCarPos, exit);
  }

  public State(State pastState, int vehicleToMove, int dir) {
    this(pastState.getCarList(), pastState.getRedCar(), pastState.getExit());
    carList.()
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
          midCoor = new Coor(start.getRow(), (start.getCol() + end.getCol()) / 2);
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

  public boolean done() {
    return redCar.canExit(this);
  }

  public ArrayList<Vehicle> getCarList() {
    return carList;
  }

  public RedCar getRedCar() {
    return redCar;
  }

  public int totalVehicles() {
    return carList.size();
  }

  public void printState() {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (board[i][j]) {
          System.out.print("1 ");
        } else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public boolean canMoveA(int index) {
    return carList.get(index).canMoveA(this);
  }

  public boolean canMoveB(int index) {
    return carList.get(index).canMoveB(this);
  }

  public void moveA(int index) {
    carList.get(index).moveA(this);
  }

  public void moveB(int index) {
    carList.get(index).moveB(this);
  }
}
