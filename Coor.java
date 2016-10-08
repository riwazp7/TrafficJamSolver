/* Coor.java
 * (c) RIWAZ POUDYAL 2016
 * immutable POJO to hold a coordinate
 */

public class Coor {

  private final int row;
  private final int col;

  public Coor(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public String toString() {
    return ("("+row+","+col+")");
  }
}
