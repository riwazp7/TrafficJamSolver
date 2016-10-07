public interface Vehicle {
  public boolean isTruck();
  public boolean isTopDown();
  public Coor getStart();
  public Coor getEnd();
  // Assume direction A is up for up-down vehicles
  //and right for right-left vehicles
  public boolean canMoveA(State state);
  public boolean canMoveB(State state);
  void moveA(State state);
  void moveB(State state);
}
