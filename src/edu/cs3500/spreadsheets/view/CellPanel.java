package edu.cs3500.spreadsheets.view;

/**
 * Interface representing a panel of Cells.
 */
public interface CellPanel {

  /**
   * selects a cell, giving it some kind of a distinguishing feature.
   */
  void select();

  /**
   * deselects a cell, removes the distinguishing feature.
   */
  void deselect();
}
