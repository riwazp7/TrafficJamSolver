public class State {

  boolean[][] board;
  ArrayList<Vehicle> carList;
  Vehicle redCar;

  public State(int row, int col, ArrayList<Pair<Coor, Coor>> positions, Pair<Coor, Coor> redCarPos) {
    carList = new ArrayList<Vehicle>();
    board = new int[row][col];
    for (Pair pair : positions) {
      if (AbstractVehicle.isTruck(pair)) {
        carList.add(new Truck(pair));
      } else {
        carList.add(new Car(pair));
      }
    }
    redCar = new RedCar(redCarPos);
    carList.add(redCar);
    markBoard(carList);
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

  public void isMarked(Coor coor) {
    return board[coor.getRow()][coor.getCol()];
  }

}
