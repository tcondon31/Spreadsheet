package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.EvaluateCellVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * represents a cell in a spreadsheet.
 */
public class Cell {

  String contents;

  public Cell(String c) {
    this.contents = c;
  }

  public Sexp parseCell() {
    if (contents.startsWith("=")) {
      return Parser.parse(contents.substring(1));
    }
    else {
      return Parser.parse(contents);
    }
  }

  public Sexp evaluateCell() {
    return this.parseCell().accept(new EvaluateCellVisitor());
  }
}
