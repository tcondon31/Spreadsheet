package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.EvaluateCell;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.GetAllRef;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a single spreadsheet. Holds a HashMap of Cells whose keys are the string evaluation of
 * their position.
 */
public class Worksheet implements IWorksheet {

  private HashMap<String, WorksheetCell> sheet;

  /**
   * constructs a new Worksheet represented by a blank hashmap.
   */
  public Worksheet() {
    this.sheet = new HashMap<>();
  }

  @Override
  public WorksheetCell getCellAt(String key) {
    return this.sheet.getOrDefault(key, new Cell(""));
  }

  @Override
  public WorksheetCell getCellAt(int col, int row) {
    String key = new Coord(col, row).toString();
    return this.sheet.getOrDefault(key, new Cell(""));
  }

  @Override
  public void addCell(int col, int row, WorksheetCell c) {
    Coord temp = new Coord(col, row);
    this.sheet.put(temp.toString(), c);
  }

  @Override
  public Sexp evaluateCell(String key) {
    if (!this.containsKey(key)) {
      return new SSymbol("");
    }
    WorksheetCell c = this.sheet.getOrDefault(key, new Cell(""));
    Set<String> ref = this.getListOfReferences(c, new ArrayList<>());
    if (ref.contains(key)) {
      throw new IllegalArgumentException("Cyclic reference in cell");
    }
    if (c.getContents().startsWith("=")) {
      return new EvaluateCell(this).apply(Parser.parse(c.getContents().substring(1)));
    } else {
      try {
        double value = Double.parseDouble(c.getContents());
        return new SNumber(value);
      } catch (Exception e) {
        switch (c.getContents()) {
          case "true":
            return new SBoolean(true);
          case "false":
            return new SBoolean(false);
          default:
            return new SString(c.getContents());
        }
      }
    }
  }

  @Override
  public List<Sexp> evaluateAllCells() {
    List<Sexp> result = new ArrayList<>();
    for (String key : this.sheet.keySet()) {
      result.add(this.evaluateCell(key));
    }
    return result;
  }

  @Override
  public boolean isValidName(String cell) {
    for (int i = 0; i < cell.length(); i++) {
      if (Character.isDigit(cell.charAt(i))) {
        try {
          String celCol = cell.substring(0, i);
          int celRow = Integer.parseInt(cell.substring(i));
          return ((!celCol.equals(""))
                  && (celCol.matches("^[a-zA-Z]*$")));
        } catch (Exception e) {
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public List<Sexp> getAllReferences(Coord tl, Coord br) {
    List<Sexp> references = new ArrayList<>();
    for (int i = tl.col; i <= br.col; i++) {
      for (int j = tl.row; j <= br.row; j++) {
        Coord temp = new Coord(i, j);
        references.add(new SSymbol(temp.toString()));
      }
    }
    return references;
  }

  @Override
  public Set<String> getListOfReferences(WorksheetCell c, List<String> list) {
    if (c.getContents().substring(0, 1).equals("=")) {
      Sexp s = Parser.parse(c.getContents().substring(1));
      Set<String> uniqueRef = new HashSet<String>(new GetAllRef(this, list).apply(s));
      return uniqueRef;
    }
    return new HashSet<String>();
  }

  @Override
  public boolean containsKey(String key) {
    return this.sheet.containsKey(key);
  }

  @Override
  public Set<String> getAllCellIndices() {
    return this.sheet.keySet();
  }

  @Override
  public int getColumnIndex(String key) {
    String col = "";
    String character;
    for (int i = 0; i < key.length(); i++) {
      character = key.substring(i, i + 1);
      try {
        Integer.parseInt(character);
      } catch (NumberFormatException e) {
        col += character;
      }
    }
    return Coord.colNameToIndex(col);
  }

  @Override
  public int getRowIndex(String key) {
    String rest = key;
    for (int i = 0; i <= key.length(); i++) {
      try {
        return Integer.parseInt(rest);
      } catch (NumberFormatException e) {
        try {
          rest = key.substring(i);
        } catch (IndexOutOfBoundsException ioobe) {
          return -1;
        }
      }
    }
    return -1;
  }

  @Override
  public void editCell(String key, String contents) {
    this.getCellAt(key).editContents(contents);
  }

  @Override
  public void removeCell(String key) {
    this.sheet.remove(this.getCellAt(key));
  }

  @Override
  public int getNumCells() {
    return this.sheet.size();
  }
}
