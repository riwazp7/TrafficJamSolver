public Interface Vehicle {
  public boolean isTruck();
  public Coor getStart();
  public Coor getEnd();
  public boolean canMoveA(State state);
  public boolean canMoveB(State state);
  void moveA(State state);
  void moveB(State state);
}
