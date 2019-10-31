package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;
import edu.cs3500.spreadsheets.model.Worksheet;

public class EvaluateCell implements Func<Sexp, Sexp>,SexpVisitor<Sexp> {

  private Worksheet worksheet;

  public EvaluateCell(Worksheet w) {
    this.worksheet = w;
  }

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
    if (this.worksheet.containsKey(s)) {
      Cell c = this.worksheet.getKey(s);
      try {
        return this.worksheet.evaluateCell(c);
      }
      catch (Exception e) {
        throw new IllegalArgumentException("Could not evaluate symbol");
      }
    }
    else if (this.worksheet.isValidName(s)) {
      return new SString("");
    }
    else {
      throw new IllegalArgumentException("Could not evaluate symbol");
    }
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
          double totalSum = 0;
          for (Sexp s : rest) {
            try {
              totalSum += new SumFunc(this.worksheet).apply(s);
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
              totalProd *= new ProdFunc(this.worksheet).apply(s);
              //totalProd *= Double.parseDouble(s.accept(new EvaluateCell()).toString());
            } catch (Exception e) {
              throw new IllegalArgumentException("Not a number");
            }
          }
          return new SNumber(totalProd);
        case "<":
          if (rest.size() > 2) {
            throw new IllegalArgumentException("Too many arguments in less than");
          }
          double firstElement;
          double secondElement;
          try {
            firstElement = new GreaterThanFunc(this.worksheet).apply(rest.get(0));
            secondElement = new GreaterThanFunc(this.worksheet).apply(rest.get(1));

            //firstElement = Double.parseDouble(new EvaluateCell(this.worksheet).apply(rest.get(0)).toString());
            //secondElement = Double.parseDouble(new EvaluateCell(this.worksheet).apply(rest.get(1)).toString());
          } catch (Exception e) {
            throw new IllegalArgumentException("Not a number");
          }
          return new SBoolean(firstElement < secondElement);
        case "CONCAT" :
          StringBuilder total = new StringBuilder();
          for (Sexp s : rest) {
            total.append(new ConcatFunc(this.worksheet).apply(s));
            //total.append(new EvaluateCell(this.worksheet).apply(s).toString());
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
