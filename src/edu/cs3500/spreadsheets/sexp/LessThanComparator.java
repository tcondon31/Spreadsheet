package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;
import edu.cs3500.spreadsheets.model.Worksheet;

public class LessThanComparator implements Func<Sexp, Double>, SexpVisitor<Double> {

  private Worksheet worksheet;

  public LessThanComparator(Worksheet worksheet) {
    this.worksheet = worksheet;
  }

  @Override
  public Double apply(Sexp arg) {
    return arg.accept(this);
  }

  @Override
  public Double visitBoolean(boolean b) {
    throw new IllegalArgumentException("Cannot compare booleans with <");
  }

  @Override
  public Double visitNumber(double d) {
    return d;
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    String first = l.get(0).toString();
    if (SexpFunction.isOneOf(first)) {
      try {
        return Double.parseDouble(new EvaluateCell(this.worksheet).apply(new SList(l)).toString());
      }
      catch (NumberFormatException e) {
        throw new IllegalArgumentException("Not a valid S-Expression");
      }
    }
    else {
      return new LessThanComparator(this.worksheet).apply(l.get(0))
              - new LessThanComparator(this.worksheet).apply(l.get(1));
    }
  }

  @Override
  public Double visitSymbol(String s) {
    if (this.worksheet.containsKey(s)) {
      Cell c = this.worksheet.getKey(s);
      try {
        return Double.parseDouble(this.worksheet.evaluateCell(c).toString());
      }
      catch (Exception e) {
        throw new IllegalArgumentException("Cannot reference");
      }
    }
    throw new IllegalArgumentException("Cannot evaluate symbol");
  }

  @Override
  public Double visitString(String s) {
    throw new IllegalArgumentException("Cannot compare Strings with <");
  }
}
