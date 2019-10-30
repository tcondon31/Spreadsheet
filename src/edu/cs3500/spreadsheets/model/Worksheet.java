package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.EvaluateCell;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;

import java.util.HashMap;
import java.util.List;

public class Worksheet {

  private HashMap<String, Cell> sheet;

  public Worksheet() {
    this.sheet = new HashMap<>();
  }

  public void addCell(int row, int col, Cell c) {
    Coord temp = new Coord(row, col);
    this.sheet.put(temp.toString(), c);
  }

  public void evaluateAll() {
    //this.sheet.forEach((k, v) -> v.evaluateCell());
  }
/*
  public Sexp evaluateReference(SSymbol s) {
    String key = s.toString();
    if (this.sheet.containsKey(key)) {
      Cell c = this.sheet.get(key);
      return c.evaluateCell();
    }
    else if (key.contains(":")) {
      String left = key.substring(0, key.indexOf(":"));
      String right = key.substring(key.indexOf(":") + 1);
      if (this.sheet.containsKey(left) && this.sheet.containsKey(right)) {
        List<Cell> references = this.getAllReferences(left, right);
      }
    }
  }

  // returns a list of all cells in a given reference and throws exception if its not
  // a valid reference
  private List<Cell> getAllReferences() {
  }

 */
}
