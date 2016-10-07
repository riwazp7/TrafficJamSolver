public class Truck extends AbstractVehicle {

  public Truck(Pair<Coor, Coor> pair) {
    this.start = pair.x;
    this.end = pair.y;
  }
}
