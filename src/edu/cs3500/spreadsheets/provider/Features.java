package edu.cs3500.spreadsheets.provider;

import javax.swing.JTextField;

/**
 * Interface for a Worksheet commander using the features interface/pattern.
 */
public interface Features {

  /**
   * Changes the contents of the model at the given coordinate. IF the cell already exists, then
   * it updates the contents of the cell with the given contents. If the cell does not exist
   * creates a cell in the model at those coordinates with the given contents.
   * @param col the column of the cell to be edited
   * @param row the row of the cell to be edited
   * @param text the contents of the cell to be added/updated
   */
  void editModel(int col, int row, JTextField text);

  /**
   * Starts the controller.
   */
  void controllerStart();

  /**
   * Saves the current values of the spreadsheet ot a file.
   */
  void saveSpreadsheet();
}
