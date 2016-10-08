import java.util.ArrayList;

public final class TrafficJam {

  public static void main(String[] args) {
    State stateA = TrafficJam.getStateA();
    TrafficJam.dfs(stateA);
  }

  public static boolean dfs(State state) {
      boolean found = false;
      for (int i = 0; i < state.totalVehicles(); i++) {

      }
      return false;
  }

  public static State getStateA() {
    ArrayList<Pair<Coor, Coor>> vehicles = new ArrayList<Pair<Coor, Coor>>();

    vehicles.add(new Pair<Coor, Coor>(new Coor(0,3), new Coor(1,3)));
    vehicles.add(new Pair<Coor, Coor>(new Coor(0,4), new Coor(0,5)));
    vehicles.add(new Pair<Coor, Coor>(new Coor(1,4), new Coor(1,5)));
    vehicles.add(new Pair<Coor, Coor>(new Coor(2,0), new Coor(3,0)));
    vehicles.add(new Pair<Coor, Coor>(new Coor(2,1), new Coor(2,3)));
    vehicles.add(new Pair<Coor, Coor>(new Coor(2,4), new Coor(2,5)));
    vehicles.add(new Pair<Coor, Coor>(new Coor(4,2), new Coor(4,4)));

    Pair<Coor, Coor> red = new Pair<Coor, Coor>(new Coor(3,5), new Coor(4,5));

    State state = new State(vehicles, red, new Coor(0,5));
    return state;
  }

}
