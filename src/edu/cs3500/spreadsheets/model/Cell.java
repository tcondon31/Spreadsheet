package edu.cs3500.spreadsheets.model;

/**
 * represents a cell in a spreadsheet.
 */
public interface Cell<T> {

  /**
   * Evaluates the content of a cell.
   * @return    the evaluated contents of a cell
   */
  public T evaluateCell();
}
