package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.Worksheet;

import java.util.List;

public class ConcatFunc implements Func<Sexp, String>, SexpVisitor<String>{

  protected Worksheet worksheet;

  public ConcatFunc(Worksheet w) {
    this.worksheet = w;
  }

  @Override
  public String apply(Sexp arg) {
    return arg.accept(this);
  }

  @Override
  public String visitBoolean(boolean b) {
    return new SBoolean(b).toString();
  }

  @Override
  public String visitNumber(double d) {
    return new SNumber(d).toString();
  }

  @Override
  public String visitSList(List<Sexp> l) {
    return new EvaluateCell(this.worksheet).apply(new SList(l)).toString();
  }

  @Override
  public String visitSymbol(String s) {
    if (this.worksheet.containsKey(s)) {
      Cell c = this.worksheet.getKey(s);
      try {
        return this.worksheet.evaluateCell(c).toString();
      }
      catch (Exception e) {
        return "";
      }
    }
    else if (s.contains(":")) {
      String left = s.substring(0, s.indexOf(":"));
      String right = s.substring(s.indexOf(":") + 1);
      if (!(this.worksheet.isValidName(left) && this.worksheet.isValidName(right))) {
        return "";
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
        List<SSymbol> references = this.worksheet.getAllReferences(topLeft, bottomRight);
        StringBuilder total = new StringBuilder();
        for (SSymbol ref : references) {
          total.append(new EvaluateCell(this.worksheet).apply(ref).toString());
        }
        return total.toString();
      }
    }
    else if (this.worksheet.isValidName(s)) {
      return "";
    }
    else {
      return "";
    }
  }

  @Override
  public String visitString(String s) {
    return s;
  }
}
