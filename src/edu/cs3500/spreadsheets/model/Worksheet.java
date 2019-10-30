package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.EvaluateCell;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;

import java.util.ArrayList;
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

  public Sexp evaluateReference(SSymbol s) {
    String key = s.toString();
    if (this.sheet.containsKey(key)) {
      Cell c = this.sheet.get(key);
      return c.evaluateCell();
    }
    else if (key.contains(":")) {
      String left = key.substring(0, key.indexOf(":"));
      String right = key.substring(key.indexOf(":") + 1);
      if (!(this.isValidName(left) && this.isValidName(right))) {
        throw new IllegalArgumentException("Invalid Cell Name");
      }
      else {
        String leftCol = "";
        String rightCol = "";
        int leftRow = 0;
        int rightRow = 0;
        for (int i = 0; i < left.length(); i++) {
          if (Character.isDigit(left.charAt(i))) {
            leftCol = left.substring(0, i);
            leftRow = Integer.parseInt(left.substring(i));
            break;
          }
        }
        for (int i = 0; i < right.length(); i++) {
          if (Character.isDigit(right.charAt(i))) {
            rightCol = right.substring(0, i);
            rightRow = Integer.parseInt(right.substring(i));
            break;
          }
        }
        Coord topLeft;
        Coord bottomRight;
        if (left.toUpperCase().compareTo(right.toUpperCase()) <= 0) {
          topLeft = new Coord(Coord.colNameToIndex(leftCol), leftRow);
          bottomRight = new Coord(Coord.colNameToIndex(rightCol), rightRow);
        }
        else {
          bottomRight = new Coord(Coord.colNameToIndex(leftCol), leftRow);
          topLeft = new Coord(Coord.colNameToIndex(rightCol), rightRow);
        }
        List<String> references = this.getAllReferences(topLeft, bottomRight);
        return null; // this needs to be in function object so that function
        // can be applied to all things inside
      }
    }
    else if (this.isValidName(key)) {
      return null; // this needs to be put into function object so it can default
    }
    else {
      throw new IllegalArgumentException("Can not Evaluate Symbol");
    }
  }

  private boolean isValidName(String cell) {
    int index = -1;
    for (int i = 0; i < cell.length(); i++) {
      if (Character.isDigit(cell.charAt(i))) {
        index = i;
        try {
          String celCol = cell.substring(0, index);
          int celRow = Integer.parseInt(cell.substring(index));
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

  public List<String> getAllReferences(Coord tl, Coord br) {
    List<String> references = new ArrayList<>();
    for (int i = tl.col; i <= br.col; i++) {
      for (int j = tl.row; j <= br.row; j++) {
        Coord temp = new Coord(i, j);
        references.add(temp.toString());
      }
    }
    return references;
  }


}
