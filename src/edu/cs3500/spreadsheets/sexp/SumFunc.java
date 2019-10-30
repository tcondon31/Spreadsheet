package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Func;

public class SumFunc implements Func<Sexp, Double>, SexpVisitor<Double> {

  @Override
  public Double apply(Sexp arg) {
    return arg.accept(this);
  }

  @Override
  public Double visitBoolean(boolean b) {
    throw new IllegalArgumentException("Cannot sum a boolean");
  }

  @Override
  public Double visitNumber(double d) {
    return d;
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    double total = 0;
    for (Sexp sexp : l) {
      total += new SumFunc().apply(l.get(0));
    }
    return total;
  }

  @Override
  public Double visitSymbol(String s) {
    return null;
  }

  @Override
  public Double visitString(String s) {
    throw new IllegalArgumentException("Cannot sum a string");
  }
}
