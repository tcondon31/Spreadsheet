package edu.cs3500.spreadsheets.view;

public interface GridPanel {

  /**
   * uses the row and column to set the designated cell with the given contents
   * @param contents string contents of the cell
   * @param row int row to go to
   * @param col int column to go to
   */
  void setCell(String contents, int row, int col);

  /**
   * changes the selected cell to the given one
   * @param row int row to go to
   * @param col int col to go to
   */
  void changeSelected(int row, int col);

  /**
   * expands the grid by the given number of rows and columns
   * @param numRows int rows to add
   * @param numCols int cols to add
   */
  void expand(int numRows, int numCols);

}
