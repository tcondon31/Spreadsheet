package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;

/**
 * interface representing a panel that contains a grid.
 */
public interface GridPanel {

  /**
   * uses the row and column to set the designated cell with the given contents.
   * @param contents string contents of the cell
   * @param row int row to go to
   * @param col int column to go to
   */
  void setCell(String contents, int row, int col);

  /**
   * changes the selected cell to the given one.
   * @param row int row to go to
   * @param col int col to go to
   */
  void changeSelected(int row, int col);

  /**
   * changes the selected cell by the given amounts.
   * @param up int times to go up. negative is down
   * @param right int times to go right. negative is left
   */
  void changeSelectedBy(int up, int right);

  /**
   * expands the grid by the given number of rows and columns.
   * @param numRows int rows to add
   * @param numCols int cols to add
   */
  void expand(int numRows, int numCols);

  /**
   * returns the String contents of the Cell in the specified position.
   * @return the String contents of the Cell
   */
  String getSelectedCellKey();

}
