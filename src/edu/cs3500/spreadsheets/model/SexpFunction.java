package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.ConcatFunc;
import edu.cs3500.spreadsheets.sexp.LessThanComparator;
import edu.cs3500.spreadsheets.sexp.ProductFunc;
import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SListShallowSize;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SumFunc;

public enum SexpFunction {
  SUM ("SUM"), PRODUCT ("PRODUCT"), LESSTHAN ("<"), CONCAT ("CONCAT");

  private final String name;

  SexpFunction(String s) {
    this.name = s;
  }

  public static boolean isOneOf(String s) {
    for (SexpFunction sf : SexpFunction.values()) {
      if (sf.name().equalsIgnoreCase(s)) {
        return true;
      }
    }
    return false;
  }
  // TODO: Make sure evry input is not a list
  public static Sexp executeFunction(String s, Sexp input, Worksheet ws) {
    if (s.equalsIgnoreCase(SUM.name)) {
      return new SNumber(new SumFunc(ws).apply(input));
    }
    else if (s.equalsIgnoreCase(PRODUCT.name)) {
      return new SNumber(new ProductFunc(ws).apply(input));
    }
    else if (s.equalsIgnoreCase(LESSTHAN.name)) {
      try {
        if (new SListShallowSize().apply(input) == 2) {
          return new SBoolean(new LessThanComparator(ws).apply(input) < 0);
        }
        else{
          throw new IllegalArgumentException("Invalid number of inputs to < (Must be 2)");
        }
      }
      catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid input to <");
      }
    }
    else if (s.equalsIgnoreCase(CONCAT.name)){
      return new SString(new ConcatFunc(ws).apply(input));
    }
    else {
      throw new IllegalArgumentException("Invalid Function Symbol");
    }
  }
}
