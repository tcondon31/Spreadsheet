package edu.cs3500.spreadsheets.provider;

import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.SpreadSheetModel;

/**
 * Represents a Formula. A formula is one of: Reference, Function, and Value.
 */
public interface FormulaInterface {

  /**
   * Accepts a visitor and returns what the visitor outputs with respect to the type of formula that
   * this formula is.
   *
   * @param visitor a FormulaVisitor
   * @param <R>     the return type of the visitor
   * @return the output of the visitor after it visits the instances of this formula
   */
  <R> R accept(FormulaVisitor<R> visitor);

  /**
   * Evaluates this formula and returns the value represented as a ValueFormula.
   *
   * @param model the spreadsheet model that performs evaluations
   * @return this formula evaluated and represented as a ValueFormula
   */
  ValueFormula evaluate(SpreadSheetModel model);

  /**
   * Returns true if any of the coordinates in the given stack are used to evaluate this formula.
   *
   * @param seen a stack of the already visited coordinates
   * @return true if there is a repeat (cyclical reference), false otherwise
   */
  boolean depHelp(Stack<Coord> seen, SpreadSheetModel model);


  //everthing that is a formuala
  //value, reference, function
}
