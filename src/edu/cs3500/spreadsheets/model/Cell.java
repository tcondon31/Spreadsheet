package edu.cs3500.spreadsheets.model;

import java.util.Objects;

/**
 * represents a cell in a spreadsheet.
 */
public class Cell {

  private String contents;

  public Cell(String c) {
    this.contents = c;
  }

  /**
   * returns the value of the contents within the cell.
   */
  public String getContents() {
    return this.contents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cell cell = (Cell) o;
    return this.contents == ((Cell) o).getContents();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.contents);
  }
}
