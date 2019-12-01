package edu.cs3500.spreadsheets.provider;

import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.formula.ValueFormula;


/**
 * Represents a cell in a spreadsheet. User inputs content into the cell as a string, and contents
 * can be evaluated and represented as a ValueFormula.
 */
public interface CellInterface {

  /**
   * Gets the contents of this cell that the user inputted.
   *
   * @return a string representing the input the user placed into the cell
   */
  String getOriginalInput();

  /**
   * Return the evaluated contents of this cell as a ValueFormula, does not re-compute the formula
   * if it had been computed before and the cells it depends on to compute haven't changed.
   *
   * @return ValueFormula representing the evaluated input of this cell
   */
  ValueFormula evaluateCell();

  /**
   * Resets the evaluated input that is saved as a cash.
   */
  void resetEvaluatedInput();

  /**
   * Resets the saved cash of whether or not this cell depends on a cycle.
   */
  void resetCycleCash();

  /**
   * Returns true if this cell depends on a cycle.
   *
   * @return true if there is a cyclical reference, false otherwise.
   */
  boolean depOnCycle(Stack<Coord> seen);

  // <R> R accept(FormulaVisitor<R> visitor);

}
