package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Worksheet {

  private HashMap<String, Cell> sheet;

  public Worksheet() {
    this.sheet = new HashMap<>();
  }

  public Cell getCellAt(String key) {
    return this.sheet.getOrDefault(key, null);
  }

  public Cell getCellAt(int col, int row) {
    String key = new Coord(col, row).toString();
    return this.sheet.getOrDefault(key, null);
  }

  public void addCell(int col, int row, Cell c) {
    Coord temp = new Coord(col, row);
    this.sheet.put(temp.toString(), c);
  }

  public void evaluateAll() {
    this.sheet.forEach((k, v) -> this.sheet.put(k, new Cell(this.evaluateCell(v).toString())));
  }

  public Sexp evaluateCell(Cell c) {
    if (c.getContents().startsWith("=")) {
      return new EvaluateCell(this).apply(Parser.parse(c.getContents().substring(1)));
    }
    else {
      try {
        double value = Double.parseDouble(c.getContents());
        return new SNumber(value);
      }
      catch (Exception e) {
        switch (c.getContents()) {
          case "true" :
            return new SBoolean(true);
          case "false" :
            return new SBoolean(false);
          default :
            return new SString(c.getContents());
        }
      }
    }
  }

  public boolean isValidName(String cell) {
    for (int i = 0; i < cell.length(); i++) {
      if (Character.isDigit(cell.charAt(i))) {
        try {
          String celCol = cell.substring(0, i);
          int celRow = Integer.parseInt(cell.substring(i));
          return ((!celCol.equals(""))
              && (celCol.matches("^[a-zA-Z]*$")));
        }
        catch (Exception e) {
          return false;
        }
      }
    }
    return false;
  }

  public List<SSymbol> getAllReferences(Coord tl, Coord br) {
    List<SSymbol> references = new ArrayList<>();
    for (int i = tl.col; i <= br.col; i++) {
      for (int j = tl.row; j <= br.row; j++) {
        Coord temp = new Coord(i, j);
        references.add(new SSymbol(temp.toString()));
      }
    }
    return references;
  }

  public boolean containsKey(String key) {
    return this.sheet.containsKey(key);
  }

  public Cell getKey(String key) {
    return this.sheet.get(key);
  }
}
