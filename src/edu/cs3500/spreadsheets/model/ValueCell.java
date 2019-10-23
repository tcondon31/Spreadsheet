package edu.cs3500.spreadsheets.model;

public class ValueCell<T> implements Cell {

  protected T value;

  /**
   * constructs a value cell.
   * @param value   value to be placed in cell
   */
  public ValueCell(T value) {
    this.value = value;
  }

  @Override
  public T evaluateCell() {
    return this.value;
  }
}
