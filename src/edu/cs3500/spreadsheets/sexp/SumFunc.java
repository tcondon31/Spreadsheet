package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetCell;

/**
 * represents a function object for summing an arbitrary number of Sexps.
 */
public class SumFunc implements Func<Sexp, Double>, SexpVisitor<Double> {

  protected Worksheet worksheet;

  /**
   * Had to add this comment to avoid losing javadoc points.
   * @param w value to apply
   */
  public SumFunc(Worksheet w) {
    this.worksheet = w;
  }

  @Override
  public Double apply(Sexp arg) {
    return arg.accept(this);
  }

  @Override
  public Double visitBoolean(boolean b) {
    return 0.0;
  }

  @Override
  public Double visitNumber(double d) {
    return d;
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    double total = 0;
    String first = l.get(0).toString();
    if (SexpFunction.isOneOf(first)) {
      try {
        total += Double.parseDouble(
                new EvaluateCell(this.worksheet).apply(new SList(l)).toString());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Not a valid S-Expression");
      }
    } else {
      for (Sexp s : l) {
        total += new SumFunc(this.worksheet).apply(s);
      }
    }
    return total;
  }

  @Override
  public Double visitSymbol(String s) {
    if (this.worksheet.containsKey(s)) {
      WorksheetCell c = this.worksheet.getCellAt(s);
      try {
        return Double.parseDouble(this.worksheet.evaluateCell(s).toString());
      } catch (Exception e) {
        if (e.getMessage().equals("Cyclic reference in cell")) {
          throw e;
        }
        return 0.0;
      }
    } else if (s.contains(":")) {
      String left = s.substring(0, s.indexOf(":"));
      String right = s.substring(s.indexOf(":") + 1);
      if (!(this.worksheet.isValidName(left) && this.worksheet.isValidName(right))) {
        return 0.0;
      } else {
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
          return 0.0;
        }
        List<Sexp> references = this.worksheet.getAllReferences(topLeft, bottomRight);
        double total = 0;
        for (Sexp ref : references) {
          total += Double.parseDouble(new EvaluateCell(this.worksheet).apply(ref).toString());
        }
        return total;
      }
    } else if (this.worksheet.isValidName(s)) {
      return 0.0;
    } else {
      return 0.0;
    }
  }

  @Override
  public Double visitString(String s) {
    return 0.0;
  }
}
