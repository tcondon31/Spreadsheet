package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;

import java.io.IOException;

/**
 * Interface for any type of view. Creates a visual representation of the model.
 */
public interface IWorksheetView {

  /**
   * renders a view of the model.
   * @throws IOException if the appendable cannot be appended onto
   */
  void render();

  /**
   * Display this view.
   */
  void display();

  /**
   * returns the String contents of the Cell that is currently selected.
   * @return the contents of whatever cell is selected
   */
  String getSelectedCellContents();

  void changeSelected(int up, int right);

  void addFeatures(Features features);

  void expand(int numRows, int numCols);


}
