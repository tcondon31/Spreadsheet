package edu.cs3500.spreadsheets.model;

public class WorksheetBuilderClass implements WorksheetReader.WorksheetBuilder {
  private Worksheet worksheet;

  public WorksheetBuilderClass(Worksheet w) {
    this.worksheet = w;
  }

  @Override
  public WorksheetReader.WorksheetBuilder createCell(int col, int row, String contents) {
    int value;
    Cell c;
    try {
      value = Integer.parseInt(contents);
      c = new ValueCell(value);
    }
    catch (Exception e) {
      try {
        if (contents instanceof )
      }
    }

    return null;
  }

  @Override
  public Object createWorksheet() {
    return null;
  }
}
