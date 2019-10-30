package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class WorksheetBuilderClass implements WorksheetReader.WorksheetBuilder {
  private Worksheet worksheet;

  public WorksheetBuilderClass(Worksheet w) {
    this.worksheet = w;
  }

  @Override
  public WorksheetReader.WorksheetBuilder createCell(int col, int row, String contents) {
    this.worksheet.addCell(row, col, new Cell(contents));
    return this;
  }

  @Override
  public Object createWorksheet() {
    this.worksheet.evaluateAll();
    return this.worksheet;
  }
}
