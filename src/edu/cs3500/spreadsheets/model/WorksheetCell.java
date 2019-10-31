package edu.cs3500.spreadsheets.model;

/**
 * A cell in a worksheet that can be modified and evaluated.
 */
public interface WorksheetCell {

  /**
   * returns the value of the contents within the cell.
   */
  public String getContents();

}
