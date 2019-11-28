package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;

import java.io.IOException;

/**
 * Interface for any type of view. Creates a visual representation of the model.
 */
public interface IWorksheetView {

  /**
   * renders a view of the model.
   *
   * @throws IOException if the appendable cannot be appended onto
   */
  void render();

  /**
   * Display this view.
   */
  void display();

  /**
   * returns the String contents of the Cell that is currently selected.
   *
   * @return the contents of whatever cell is selected
   */
  String getSelectedCellContents();

  /**
   * changes the selected cell by the specified amount up and to the right.
   *
   * @param up    positive means up, negative means down
   * @param right positive means right, negative means left
   */
  void changeSelected(int up, int right);

  /**
   * communication between view and controller for listeners and callbacks.
   *
   * @param features the controller
   */
  void addFeatures(Features features);

  /**
   * expands the view by the given dimensions.
   *
   * @param numRows number of rows to add
   * @param numCols number of columns to add
   */
  void expand(int numRows, int numCols);

  /**
   * resets the focus of the view for keyEvents.
   */
  void resetFocus();

  /**
   * repaints the view immediately.
   */
  void repaintImmediately();

  /**
   * updates the text field of the view if applicable.
   */
  void updateTextField();

}
