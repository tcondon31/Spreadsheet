package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.*;

/**
 * represents a cell in a spreadsheet.
 */
public class Cell {

  private String contents;

  public Cell(String c) {
    this.contents = c;
  }

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
}
