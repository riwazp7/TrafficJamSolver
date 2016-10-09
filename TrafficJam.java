/* TrafficJam.java
 * (c) RIWAZ POUDYAL 2016
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;

public final class TrafficJam {

  public static void main(String[] args) {
    State stateA = TrafficJam.getStateA();


    HashSet<State> seen = new HashSet<State>();
    seen.add(stateA);
    PriorityQueue<State> queue = stateA.getAllAdjacentStates();
    while (true) {
      PriorityQueue<State> newQueue = queue.poll().getAllAdjacentStates();
      for (State state : newQueue) {
        if (state.done()) {
          System.out.println("****DONE****");
          state.printBoardState();
          return;
        }
        if (!seen.contains(state)) {
          seen.add(state);
          queue.add(state);
        }
      }
    }

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

  public static State getStateB() {
    ArrayList<Pair<Coor, Coor>> vehicles = new ArrayList<Pair<Coor, Coor>>();
    vehicles.add(new Pair<Coor,Coor>(new Coor(0,0), new Coor(2,0)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(0,1), new Coor(2,1)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(0,2), new Coor(1,2)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(0,3), new Coor(0,5)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(1,3), new Coor(1,5)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(2,2), new Coor(2,3)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(2,4), new Coor(2,5)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(3,0), new Coor(3,1)));
    vehicles.add(new Pair<Coor,Coor>(new Coor(5,2), new Coor(5,3)));

    Pair<Coor, Coor> red = new Pair<Coor, Coor>(new Coor(3,4), new Coor(4,4));

    State state = new State(vehicles, red, new Coor(0,4));
    return state;
  }

}
