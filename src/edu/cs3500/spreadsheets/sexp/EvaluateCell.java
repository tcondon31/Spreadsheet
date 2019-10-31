package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Func;
import edu.cs3500.spreadsheets.model.SexpFunction;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * Function object designed to evaluate a cell, then delegates to other objects
 * depending on what function it should call, or evaluates it here if it is
 * not a formula.
 */
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
      Cell c = this.worksheet.getCellAt(s);
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
        return SexpFunction.executeFunction(firstString, rest.get(0), this.worksheet);
      }
      else {
        return SexpFunction.executeFunction(
                firstString, new SList(this.getValidList(rest)), this.worksheet);
      }
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
