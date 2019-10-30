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

  public Sexp evaluateCell() {
    if (this.contents.startsWith("=")) {
      return new EvaluateCell().apply(Parser.parse(this.contents.substring(1)));
    }
    else {
      try {
        double value = Double.parseDouble(this.contents);
        return new SNumber(value);
      }
      catch (Exception e) {
        switch (this.contents) {
          case "true" :
            return new SBoolean(true);
          case "false" :
            return new SBoolean(false);
          default :
            return new SString(this.contents);
        }
      }
    }
  }
}
