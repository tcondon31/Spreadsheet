package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.Func;

public class SumFunc implements Func<Sexp, Sexp>, SexpVisitor<Sexp> {
  @Override
  public Sexp apply(Sexp arg) {
    return null;
  }

  @Override
  public Sexp visitBoolean(boolean b) {
    return null;
  }

  @Override
  public Sexp visitNumber(double d) {
    return null;
  }

  @Override
  public Sexp visitSList(List<Sexp> l) {
    return null;
  }

  @Override
  public Sexp visitSymbol(String s) {
    return null;
  }

  @Override
  public Sexp visitString(String s) {
    return null;
  }
}
