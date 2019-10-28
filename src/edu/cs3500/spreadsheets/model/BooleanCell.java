package edu.cs3500.spreadsheets.model;

public class BooleanCell implements Cell{
  boolean value;

  public BooleanCell(boolean val) {
    this.value = val;
  }

  @Override
  public Object evaluateCell() {
    return this.value;
  }
}
