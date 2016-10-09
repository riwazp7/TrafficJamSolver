/* State.java
 * (c) RIWAZ POUDYAL 2016
 */
import java.util.ArrayList;
import java.util.PriorityQueue;

public class State implements Comparable<State> {

  // Default max no of rows and cols
  private final static int DEFAULT_ROW = 6;
  private final static int DEFAULT_COL = 6;

  // Moves taken so far from the initial state.
  int movesSoFar;

  // Tracks empty coordinates on the board
  boolean[][] board;

  // List of all the cars in the board. DOES NOT contain the red car.
  ArrayList<Vehicle> carList;

  // The red car
  RedCar redCar;

  // The coordinate the red car must exit through.
  Coor exit;

  // Number of rows and cols
  private int row;
  private int col;

  /*
   * Build a state out of an ArrayList of Pairs of vehicle coordinates,
   * position of the red car, and coordinate of exit.
   */
  public State(int row, int col, ArrayList<Pair<Coor, Coor>> positions, Pair<Coor, Coor> redCarPos, Coor exit) {
    this.row = row;
    this.col = col;
    this.exit = exit;
    this.movesSoFar = 0;
    carList = new ArrayList<Vehicle>();
    board = new boolean[row][col];
    for (Pair<Coor, Coor> pair : positions) {
      carList.add(new Vehicle(pair));
    }
    this.redCar = new RedCar(redCarPos);
    //carList.add(redCar);
    markBoard();
  }

  public State(ArrayList<Pair<Coor, Coor>> positions, Pair<Coor, Coor> redCarPos, Coor exit) {
    this(DEFAULT_ROW, DEFAULT_COL, positions, redCarPos, exit);
  }

  /*
   * Build a new State identical to the given state.
   */
  public State(State pastState) {
    ArrayList<Vehicle> carList = new ArrayList<Vehicle>();
    for (Vehicle vehicle : pastState.getCarList()) {
      carList.add(new Vehicle(vehicle));
    }
    this.row = pastState.getRow();
    this.col =  pastState.getCol();
    this.board = new boolean[row][col];
    this.carList = carList;
    this.redCar = new RedCar(pastState.getRedCar());
    this.exit = pastState.getExit();
    this.movesSoFar = pastState.getMovesSoFar();
    markBoard();
  }

  // Mark the board based on cars in CarList.
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
    markCoor(redCar.getStart());
    markCoor(redCar.getEnd());
  }

  // Use carefully
  public void markCoor(Coor coor) {
    board[coor.getRow()][coor.getCol()] = true;
  }

  public void unmarkCoor(Coor coor) {
    board[coor.getRow()][coor.getCol()] = false;
  }

  // Handles out of board coordinates too
  public boolean isMarked(Coor coor) {
    if (coor.getRow() >= row || coor.getRow() < 0 || coor.getCol() >= col || coor.getCol() < 0) {
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

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getMovesSoFar() {
    return movesSoFar;
  }

  public int totalVehicles() {
    return carList.size();
  }

  public void printBoardState() {
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
    movesSoFar += 1;
    carList.get(index).moveA(this);
  }

  public void moveB(int index) {
    movesSoFar += 1;
    carList.get(index).moveB(this);
  }

  public PriorityQueue<State> getAllAdjacentStates() {
    PriorityQueue<State> result = new PriorityQueue<State>();
    for (int i = 0; i < carList.size(); i++) {
      if (canMoveA(i)) {
        State newState = new State(this);
        newState.moveA(i);
        result.add(newState);
      }
      if (canMoveB(i)) {
        State newState = new State(this);
        newState.moveB(i);
        result.add(newState);
      }
    }
    return result;
  }

  public int compareTo(State s) {
    return getEstimatedCost().compareTo(s.getEstimatedCost());
  }

  public Integer getEstimatedCost() {
    return new Integer(redCar.getHeuristicValue(this) + this.movesSoFar);
  }

  public boolean equals(State s) {
    for (int i = 0; i < carList.size(); i++) {
      if (!s.getCarList().get(i).equals(carList.get(i))) {
        return false;
      }
    }
    if (!redCar.equals(s.getRedCar())) {
      return false;
    }
    return true;
  }

  public int hashCode() {
    String hash = "";
    for (int j = 0; j < col; j++) {
      if (board[2][j]) hash += "1";
      else hash += "0";
    }
    return Integer.parseInt(hash);
  }
}
