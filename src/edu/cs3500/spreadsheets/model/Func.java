package edu.cs3500.spreadsheets.model;

/**
 * The interface for the function objects.
 *
 * @param <A> the argument type
 * @param <R> the return type
 */
public interface Func<A, R> {

  /**
   * Applies the given visitor to the specified class.
   * @param argument visitor to apply
   */
  R apply(A argument);

}
