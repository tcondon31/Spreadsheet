package edu.cs3500.spreadsheets.model;

/**
 * A cell in a worksheet that can be modified and evaluated.
 */
public interface WorksheetCell {

  /**
   * returns the value of the contents within the cell.
   */
  String getContents();

  /**
   * changes contents of this cell to the given string.
   *
   * @param newContents new contents to be set to the cell contents
   */
  void editContents(String newContents);

}
