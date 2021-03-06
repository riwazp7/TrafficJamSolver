/* State.java
 * (c) RIWAZ POUDYAL 2016
 */
import java.util.ArrayList;
import java.util.PriorityQueue;

public final class State implements Comparable<State> {

  // Moves taken so far from the initial state.
  int movesSoFar;

  // Tracks empty coordinates on the board
  boolean[][] board;

  // List of all the cars in the board. Contains the red car as the last entry.
  ArrayList<Vehicle> carList;

  // The red car
  RedCar redCar;

  // The coordinate the red car must exit through.
  Coor exit;

  // Number of rows and cols
  private int row = 6;
  private int col = 6;

  ArrayList<State> stateProgression = new ArrayList<State>();

  /*
   * Build a state out of an ArrayList of Pairs of vehicle coordinates,
   * position of the red car, and coordinate of exit.
   */
  public State(ArrayList<Pair<Coor, Coor>> positions, Pair<Coor, Coor> redCarPos, Coor exit) {
    carList = new ArrayList<Vehicle>();
    this.exit = exit;
    this.movesSoFar = 0;
    this.redCar = new RedCar(redCarPos);
    board = new boolean[row][col];
    for (Pair<Coor, Coor> pair : positions) {
      carList.add(new Vehicle(pair));
    }
    carList.add(redCar);
    markBoard();
    stateProgression.add(this);
  }

  /*
   * Build a new State identical to the given state.
   */
  public State(State pastState) {
    this.redCar = new RedCar(pastState.getRedCar());
    ArrayList<Vehicle> carList = new ArrayList<Vehicle>();
    ArrayList<Vehicle> oldCarList = pastState.getCarList();
    for (int i = 0; i < oldCarList.size() - 1; i++) {
      carList.add(new Vehicle(oldCarList.get(i)));
    }
    carList.add(redCar);
    this.carList = carList;
    this.board = new boolean[row][col];
    this.exit = pastState.getExit();
    this.movesSoFar = pastState.getMovesSoFar();
    boolean[][] oldBoard = pastState.getBoard();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        board[i][j] = oldBoard[i][j];
      }
    }
    for (State st : pastState.getStateProgression()) {
      stateProgression.add(st);
    }
    stateProgression.add(this);
  }

  /*
   *  Methods related to marking or checking a mark in the board
   */

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
  }

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

  /*
   * Getter methods for state variables
   */
  public Coor getExit() {
    return exit;
  }

  public ArrayList<Vehicle> getCarList() {
    return carList;
  }

  public boolean[][] getBoard() {
    return board;
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

  public ArrayList<State> getStateProgression() {
    return stateProgression;
  }

  // Check if this state is the goal state
  public boolean done() {
    return redCar.canExit(this);
  }

  public String getBoardState() {
    String result = "";
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (board[i][j]) {
          result+=("1 ");
        } else {
          result+=("0 ");
        }
      }
      result += "\n";
    }
    result += "\n";
    return result;
  }

  /*
   * Methods to move vehicles at the given index
   */
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

  // Returns all adjacent states that can be reached by moving one car one move
  // in any direction
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

  // Compares two states based on our evaluation function
  public int compareTo(State s) {
    return getEstimatedCost().compareTo(s.getEstimatedCost());
  }

  // The evaluation function
  public Integer getEstimatedCost() {
    return new Integer(redCar.getHeuristicValue(this) + this.movesSoFar);
  }

  public String toString() {
    String result = getBoardState();
    for (Vehicle vehicle : carList) {
      result += (vehicle.toString() + "\n");
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof State) {
      State s = (State) obj;
      for (int i = 0; i < carList.size(); i++) {
        if (!s.getCarList().get(i).equals(carList.get(i))) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /* Unused function.
   * Can be used to increase speed for bigger puzzles.
   */
  public int hashCode() {
    int hash = carList.get(0).getStart().getRow() * 10 + carList.get(0).getStart().getCol();
    hash = hash * 100 + redCar.getStart().getRow()* 10 + redCar.getStart().getCol();
    hash = hash * 100 + carList.get(3).getStart().getRow() * 10 + carList.get(3).getStart().getCol();
    return hash;
  }
}
