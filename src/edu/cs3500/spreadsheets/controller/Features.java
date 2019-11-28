package edu.cs3500.spreadsheets.controller;

/**
 * Interface for a controller. Contains methods that can alter the state of a model.
 */
public interface Features {

  /**
   * Changes a cells contents to something new.
   *
   * @param cellKey     the location of the cell to be changed
   * @param newContents the new contents of the cell
   */
  void changeCellContents(String cellKey, String newContents);

  /**
   * Returns a cell's contents to its original. Acts as a cancel to an edit.
   *
   * @param cellKey the location of the cell
   * @return the String contents of the original
   */
  String rejectEdits(String cellKey);

  /**
   * Changes the selected cell in the grid.
   *
   * @param direction the direction to move the selection
   */
  void changeSelected(String direction);

  /**
   * resets the focus of the view for keyEvents.
   */
  void resetFocus();

  /**
   * clears the contents of the currently selected cell.
   */
  void clearCell(String cellKey);

}
