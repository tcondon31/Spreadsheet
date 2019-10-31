package edu.cs3500.spreadsheets.model;

public interface Comparator<A> {

  Boolean apply (A arg1, A arg2);

}
