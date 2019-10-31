package edu.cs3500.spreadsheets.model;

/**
 * The interface that allows function objects to work.
 * @param <A> the argument type
 */
public interface Comparator<A> {

  Boolean apply(A argument1, A argument2);

}
