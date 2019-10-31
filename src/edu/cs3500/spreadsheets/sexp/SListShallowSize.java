package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Func;

public class SListShallowSize implements Func<Sexp, Double>, SexpVisitor<Double> {

  @Override
  public Double apply(Sexp arg) {
    return arg.accept(this);
  }

  @Override
  public Double visitBoolean(boolean b) {
    throw new IllegalArgumentException("Cannot get size, not an SList");
  }

  @Override
  public Double visitNumber(double d) {
    throw new IllegalArgumentException("Cannot get size, not an SList");
  }

  @Override
  public Double visitSList(List<Sexp> l) {
    return (double)l.size();
  }

  @Override
  public Double visitSymbol(String s) {
    throw new IllegalArgumentException("Cannot get size, not an SList");
  }

  @Override
  public Double visitString(String s) {
    throw new IllegalArgumentException("Cannot get size, not an SList");
  }
}
