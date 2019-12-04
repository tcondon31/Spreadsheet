package edu.cs3500.spreadsheets.provider;

import edu.cs3500.spreadsheets.model.Coord;


/**
 * Represents a spreadsheet. Made up of many cells, you can edit cells, evaluate them, get the
 * original user input, delete the contents.
 */
public interface SpreadSheetModel {


  void createCell(int col, int row, String contents);

  /**
   * Changes the contents of a cell by replacing the cell with a new one with the new contents.
   *
   * @param coordinates the coordinates of the cell
   * @param contents    the new contents, i.e. the contents you are entering instead
   */
  void editCellContents(Coord coordinates, String contents);


  /**
   * Return the original contents that the user put into the cell at the given coordinate.
   *
   * @param coordinates the coordinates of the cell
   * @return the users input in the cell at the given coordinate
   */
  String cellOriginalContents(Coord coordinates);

  /**
   * Return the evaluated contents of the cel at the given coordinate.
   *
   * @param cell the coordinates of the cell
   * @return the contents after they have been evaluated
   */
  ValueFormula evaluatedContents(Coord cell);

  /**
   * Get the evaluated contents of the cell at the given coordinate as a string.
   *
   * @param coord the coordinates of the cell
   * @return the evaluated contents of the cell as a string
   */
  String getEvaluatedContentsAsString(Coord coord);

  /**
   * Deletes the contents of the cell at the given coordinate by replacing it with a cell of no
   * contents.
   *
   * @param coord the coordinates of the cell
   */
  void deleteCellContents(Coord coord);

  /**
   * returns the cell at the given coordinate.
   *
   * @param coord the coordinate of the cell you want
   * @return return the cell at the given coordinate
   */
  CellInterface getCell(Coord coord);

  /**
   * Returns a Coordinate of the highest column and row in this model.
   *
   * @return a Coord with the highest column and row
   */
  public Coord getUpperBound();

  /**
   * Returns a Coordinate of the lowest column and row in this model.
   *
   * @return a Coord with the lowest column and row
   */
  public Coord getLowerBound();

}
