/* Coor.java
 * POJO to hold a coordinate
 */
 
public Class Coor {

  private int row;
  private int col;

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

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }
}
