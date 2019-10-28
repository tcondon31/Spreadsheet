package edu.cs3500.spreadsheets.model;

public class NumberCell implements Cell{
  double value;

  public NumberCell(double val) {
    this.value = val;
  }

  @Override
  public Object evaluateCell() {
    return this.value;
  }
}
