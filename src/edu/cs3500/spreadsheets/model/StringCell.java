package edu.cs3500.spreadsheets.model;

public class StringCell implements Cell{
  String value;

  public StringCell(String val) {
    this.value = val;
  }

  @Override
  public Object evaluateCell() {
    return this.value;
  }
}
