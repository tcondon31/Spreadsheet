package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;

/**
 * represents a panel that includes controls such as buttons and text fields for editing the
 * Worksheet.
 */
public interface EditBarPanel {

  /**
   * changes the text in the text field.
   *
   * @param contents contents to change it to
   */
  void changeTextField(String contents);

  /**
   * expands the size of the edit bar panel.
   *
   * @param numCellsToExpand how many cells to expand it by
   */
  void expand(int numCellsToExpand);

  /**
   * used to communicate listeners/callbacks to controller.
   *
   * @param features the controller
   */
  void addFeatures(Features features);

}
