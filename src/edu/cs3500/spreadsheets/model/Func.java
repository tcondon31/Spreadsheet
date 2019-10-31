package edu.cs3500.spreadsheets.model;

/**
 * The interface for the function objects.
 * @param <A> the argument type
 * @param <R> the return type
 */
public interface Func<A, R> {

  R apply(A argument);

}
