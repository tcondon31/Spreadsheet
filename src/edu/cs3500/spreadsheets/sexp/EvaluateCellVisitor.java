package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

public class EvaluateCellVisitor implements SexpVisitor<Sexp> {
  @Override
  public SBoolean visitBoolean(boolean b) {
    return new SBoolean(b);
  }

  @Override
  public SNumber visitNumber(double d) {
    return new SNumber(d);
  }

  @Override
  public Sexp visitSymbol(String s) {
    switch (s.toUpperCase()) {
      case "SUM" :

      case "PRODUCT" :

      case "<" :
    }
  }

  @Override
  public SString visitString(String s) {
    return new SString(s);
  }

  @Override
  public Object visitSList(List l) {
    return null;
  }
}
