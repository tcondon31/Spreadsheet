package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;

public class EvaluateCell implements Func<Sexp, Sexp>,SexpVisitor<Sexp> {

  @Override
  public Sexp apply(Sexp arg) {
    return arg.accept(this);
  }

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
      if (rest.size() < 1) {
        throw new IllegalArgumentException("Not enough arguments");
      }
      else if (rest.size() == 1) {
        return SexpFunction.executeFunction(firstString, rest.get(0));
      }
      else {
        return SexpFunction.executeFunction(firstString, new SList(this.getValidList(rest)));
      }

      /*
      switch (firstString.toUpperCase()) {
        case "SUM":
          double tot = new SumFunc().apply(new SList(rest));
          return new SNumber(tot);
          /*
          double totalSum = 0;
          for (Sexp s : rest) {
            try {
              totalSum += Double.parseDouble(s.accept(new EvaluateCell()).toString());
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SNumber(totalSum);
           */
      /*
        case "PRODUCT":
          double totalProd = 1;
          for (Sexp s : rest) {
            try {
              totalProd *= Double.parseDouble(s.accept(new EvaluateCell()).toString());
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SNumber(totalProd);
        case "<":
          double curElement;
          try {
            curElement = Double.parseDouble(rest.get(0).accept(new EvaluateCell()).toString());
          } catch (Exception e) {
            throw new IllegalArgumentException("Not a number");
          }
          List<Sexp> rest2 = rest.subList(1, rest.size());
          for (Sexp s : rest2) {
            try {
              double nextElement = Double.parseDouble(s.accept(new EvaluateCell()).toString());
              if (nextElement <= curElement) {
                return new SBoolean(false);
              }
              curElement = nextElement;
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SBoolean(true);
        case "CONCAT" :
          StringBuilder total = new StringBuilder();
          for (Sexp s : rest) {
            total.append(s.accept(new EvaluateCell()).toString());
          }
          return new SString(total.toString());
        default:
          throw new IllegalArgumentException("Invalid Function");
      }
    */
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid S-Expression");
    }
  }

  private List<Sexp> getValidList(List<Sexp> l) {
    List<Sexp> lCopy = new ArrayList<>();
    lCopy.addAll(l);
    while (SexpFunction.isOneOf(lCopy.get(0).toString())) {
      lCopy.remove(0);
    }
    return lCopy;
  }
}
