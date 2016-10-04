public class State {

  int[][] board;
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
      Coor start = 
    }
  }

  private void markCoor() {

  }

}
