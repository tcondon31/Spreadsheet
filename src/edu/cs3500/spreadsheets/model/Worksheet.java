package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a single spreadsheet.
 * Holds a HashMap of Cells whose keys are the string evaluation of their position.
 */
public class Worksheet {

  private HashMap<String, Cell> sheet;

  public Worksheet() {
    this.sheet = new HashMap<>();
  }

  public Cell getCellAt(String key) {
    return this.sheet.getOrDefault(key, null);
  }

  Cell getCellAt(int col, int row) {
    String key = new Coord(col, row).toString();
    return this.sheet.getOrDefault(key, null);
  }

  void addCell(int col, int row, Cell c) {
    Coord temp = new Coord(col, row);
    this.sheet.put(temp.toString(), c);
  }

  /**
   * Returns an Sexp containing the evaluation of the cell based on its contents.
   * @param key the key to be evaluated
   * @return Sexp the evaluated contents in an S-expression
   */
  public Sexp evaluateCell(String key) {
    Cell c = this.sheet.getOrDefault(key, new Cell(""));
    List<String> ref = this.getListOfReferences(c);
    if (ref.contains(key)) {
      throw new IllegalArgumentException("Cyclic reference in cell");
    }
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

  /**
   * returns true if the given string is a valid name for a cell.
   * @param cell the string to be evaluated
   * @return boolean whether or not the String name is a valid cell name
   */
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

  /**
   * Returns a list of all cells being referenced within two Coords.
   * @param tl the top left Coord to be evaluated
   * @param br the bottom right Coord to be evaluated
   * @return List of SSymbol the list of symbols that lie within the two bounds
   */
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

  public List<String> getListOfReferences(Cell c) {
    return this.getLoRAcc(c, new ArrayList<String>());
  }

  public List<String> getLoRAcc(Cell c, List<String> list) {
    if (c.getContents().substring(0,1).equals("=")) {
      Sexp s = Parser.parse(c.getContents().substring(1));
      return new GetAllRef(this, list).apply(s);
    }
    return new ArrayList<String>();
  }

  public boolean containsKey(String key) {
    return this.sheet.containsKey(key);
  }

  public Cell getKey(String key) {
    return this.sheet.get(key);
  }
}
