package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;

public class LessThanComparator implements Func<Sexp, Double>, SexpVisitor<Double> {

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
        return Double.parseDouble(new EvaluateCell().apply(new SList(l)).toString());
      }
      catch (NumberFormatException e) {
        throw new IllegalArgumentException("Not a valid S-Expression");
      }
    }
    else {
      return new LessThanComparator().apply(l.get(0)) - new LessThanComparator().apply(l.get(1));
    }
  }

  @Override
  public Double visitSymbol(String s) {
    return 0.0;
  }

  @Override
  public Double visitString(String s) {
    throw new IllegalArgumentException("Cannot compare Strings with <");
  }
}
