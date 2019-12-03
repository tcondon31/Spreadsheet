package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.CellInterface;
import edu.cs3500.spreadsheets.provider.ValueFormula;

import java.util.Stack;

public class CellAdapter implements CellInterface {

  WorksheetCell cell;

  public CellAdapter(WorksheetCell cell) {
    if (cell == null) {
      throw new IllegalArgumentException("Can not adapt null cell");
    }
    this.cell = cell;
  }

  @Override
  public String getOriginalInput() {
    return this.cell.getContents();
  }

  @Override
  public ValueFormula evaluateCell() {
    //TODO: This
    return null;
  }

  @Override
  public void resetEvaluatedInput() {
    // do nothing
  }

  @Override
  public void resetCycleCash() {

  }

  @Override
  public boolean depOnCycle(Stack<Coord> seen) {
    return false;
  }
}
