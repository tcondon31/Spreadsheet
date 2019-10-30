package edu.cs3500.spreadsheets.model;

public interface Func<A, R> {

  R apply(A arg);

}
