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
    throw new IllegalArgumentException("Cannot evaluate symbol");
  }

  @Override
  public SString visitString(String s) {
    return new SString(s);
  }

  @Override
  public Sexp visitSList(List l) {
    try {
      SSymbol first = (SSymbol)l.get(0);
      String firstString = first.toString();
      List<Sexp> rest = l.subList(1, l.size());
      if (rest.size() <= 1) {
        throw new IllegalArgumentException("Not enough arguments");
      }
      switch (firstString.toUpperCase()) {
        case "SUM":
          double totalSum = 0;
          for (Sexp s : rest) {
            try {
              totalSum += Double.parseDouble(s.accept(new EvaluateCellVisitor()).toString());
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SNumber(totalSum);
        case "PRODUCT":
          double totalProd = 1;
          for (Sexp s : rest) {
            try {
              totalProd *= Double.parseDouble(s.accept(new EvaluateCellVisitor()).toString());
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SNumber(totalProd);
        case "<":
          double curElement;
          try {
            curElement = Double.parseDouble(rest.get(0).accept(new EvaluateCellVisitor()).toString());
          } catch (Exception e) {
            throw new IllegalArgumentException("Not a number");
          }
          List<Sexp> rest2 = rest.subList(1, rest.size());
          for (Sexp s : rest2) {
            try {
              double nextElement = Double.parseDouble(s.accept(new EvaluateCellVisitor()).toString());
              if (nextElement <= curElement) {
                return new SBoolean(false);
              }
              curElement = nextElement;
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SBoolean(true);
        default:
          throw new IllegalArgumentException("Invalid Function");
      }
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid Symbol");
    }
  }
}
