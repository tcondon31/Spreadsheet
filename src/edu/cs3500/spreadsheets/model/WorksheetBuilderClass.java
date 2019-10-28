package edu.cs3500.spreadsheets.model;

public class WorksheetBuilderClass implements WorksheetReader.WorksheetBuilder {
  private Worksheet worksheet;

  public WorksheetBuilderClass(Worksheet w) {
    this.worksheet = w;
  }

  @Override
  public WorksheetReader.WorksheetBuilder createCell(int col, int row, String contents) {
    Double value;
    boolean value2;
    Cell c;
    if (contents == null) {
      c = null;
    }
    else if (contents.substring(0, 1).equals("=")) {
      c = new
      c = c.determineFormulaType(contents);
    }
    else {
      try {
        value = Double.parseDouble(contents);
        c = new NumberCell(value);
        this.worksheet.addCell(row, col, c);
      }
      catch (Exception e) {
        if (contents.equalsIgnoreCase("true")) {
          c = new BooleanCell(true);
          this.worksheet.addCell(row, col, c);
        }
        else if (contents.equalsIgnoreCase("false")) {
          c = new BooleanCell(false);
          this.worksheet.addCell(row, col, c);
        }
      }
    }
    return this;
  }

  @Override
  public Object createWorksheet() {
    return null;
  }
}
