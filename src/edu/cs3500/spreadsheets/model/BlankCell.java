package edu.cs3500.spreadsheets.model;

public class BlankCell implements Cell{
  @Override
  public Object evaluateCell() {
    return null;
  }
}
