package edu.cs3500.spreadsheets.provider;

import java.util.List;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * An abstracted function object for processing any formulas. A visitor that visits formulas and
 * helps them evaluate.
 *
 * @param <R> the return type of this visitor
 */
public interface FormulaVisitor<R> {

  /**
   * Process a boolean value.
   *
   * @param b the value
   * @return the desired result
   */
  R visitBoolean(Boolean b);

  /**
   * Process a double value.
   *
   * @param d the value
   * @return the desired result
   */
  R visitDouble(double d);

  /**
   * Process a string value.
   *
   * @param s the value
   * @return the desired result
   */
  R visitString(String s);

  /**
   * Process a function.
   *
   * @param func the function
   * @return the desired result
   */
  R visitFunction(IFormulaFunction func);

  /**
   * Process a reference.
   *
   * @param reference the list of coordinates that this reference is referring to
   * @return the desired result
   */
  R visitReference(List<Coord> reference);

}
