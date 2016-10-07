public class Car extends AbstractVehicle {
  public Car(Pair<Coor, Coor> pair) {
    this.start = pair.x;
    this.end = pair.y;
  }
}
