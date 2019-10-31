package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;
import edu.cs3500.spreadsheets.model.Worksheet;

public class SumFunc implements Func<Sexp, Double>, SexpVisitor<Double> {

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
        total += Double.parseDouble(new EvaluateCell().apply(new SList(l)).toString());
      }
      catch (NumberFormatException e) {
        throw new IllegalArgumentException("Not a valid S-Expression");
      }
    }
    else {
      for (Sexp s : l) {
        total += new SumFunc().apply(s);
      }
    }
    return total;
  }

  @Override
  public Double visitSymbol(String s) {
    return 0.0;
  }

  @Override
  public Double visitString(String s) {
    return 0.0;
  }
}
