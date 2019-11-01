package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Function object designed to get the list of all references within a S-Expression.
 */
public class GetAllRef implements Func<Sexp, List<String>>, SexpVisitor<List<String>> {

  private Worksheet worksheet;
  private List<String> list;

  /**
   * Constructor for the function object.
   * @param w the worksheet to be referenced
   * @param list the list of references to be passed along and added to
   */
  public GetAllRef(Worksheet w, List<String> list) {
    this.worksheet = w;
    this.list = list;
  }

  @Override
  public List<String> apply(Sexp argument) {
    return argument.accept(this);
  }

  @Override
  public List<String> visitBoolean(boolean b) {
    return this.list;
  }

  @Override
  public List<String> visitNumber(double d) {
    return this.list;
  }

  @Override
  public List<String> visitSList(List<Sexp> l) {
    for (Sexp sexp : l) {
      this.list.addAll(new GetAllRef(this.worksheet, new ArrayList<String>()).apply(sexp));
    }
    return list;
  }

  @Override
  public List<String> visitSymbol(String s) {
    if (this.worksheet.isValidName(s)) {
      WorksheetCell c = this.worksheet.getCellAt(s);
      if (!this.list.contains(s)) {
        this.list.add(s);
        this.list.addAll(this.worksheet.getListOfReferences(c, this.list));
        return this.list;
      }
    }
    else if (s.contains(":")) {
      String left = s.substring(0, s.indexOf(":"));
      String right = s.substring(s.indexOf(":") + 1);
      if (!(this.worksheet.isValidName(left) && this.worksheet.isValidName(right))) {
        return this.list;
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
        } else {
          return this.list;
        }
        List<SSymbol> references = this.worksheet.getAllReferences(topLeft, bottomRight);
        for (SSymbol refer : references) {
          this.list.addAll(new GetAllRef(this.worksheet, new ArrayList<String>()).apply(refer));
        }
        return this.list;
      }
    }
    return this.list;
  }

  @Override
  public List<String> visitString(String s) {
    return this.list;
  }
}
