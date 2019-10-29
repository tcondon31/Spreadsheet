package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    this.sheet.forEach((k, v) -> v.evaluateCell());
  }
}
