package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Worksheet;

import java.util.List;

public class GreaterThanFunc extends SumFunc {

  public GreaterThanFunc(Worksheet worksheet) {
    super(worksheet);
  }

  @Override
  public Double visitBoolean(boolean b) {
    throw new IllegalArgumentException("Cannot evaluate boolean");
  }

  @Override
  public Double visitSymbol(String s) {
    if (this.worksheet.containsKey(s)) {
      Cell c = this.worksheet.getKey(s);
      try {
        return Double.parseDouble(this.worksheet.evaluateCell(c).toString());
      }
      catch (Exception e) {
        throw new IllegalArgumentException("Cannot reference");
      }
    }
    throw new IllegalArgumentException("Cannot evaluate symbol");
  }

  @Override
  public Double visitString(String s) {
    throw new IllegalArgumentException("Cannot evaluate String");
  }
}
