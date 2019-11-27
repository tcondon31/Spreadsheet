package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * represents a cell that can hold values and formulas in a spreadsheet.
 */
public class Cell implements WorksheetCell {

  private String contents;

  /**
   * Constructs a cell.
   * @param c the string contents of the cell
   */
  public Cell(String c) {
    this.contents = c;
  }

  @Override
  public String getContents() {
    return this.contents;
  }

  @Override
  public void editContents(String newContents) {
    this.contents = newContents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorksheetCell cell = (Cell) o;
    return this.contents == ((Cell) o).getContents();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.contents);
  }
}
