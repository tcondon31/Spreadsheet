package edu.cs3500.spreadsheets.model;

/**
 * represents a class that is used to build the worksheet model.
 */
public class WorksheetBuilderClass implements WorksheetReader.WorksheetBuilder {
  private IWorksheet worksheet;

  /**
   * Constructor for the worksheetBuilder class.
   *
   * @param w the worksheet to build into
   */
  public WorksheetBuilderClass(IWorksheet w) {
    this.worksheet = w;
  }

  @Override
  public WorksheetReader.WorksheetBuilder createCell(int col, int row, String contents) {
    this.worksheet.addCell(col, row, new Cell(contents));
    return this;
  }

  @Override
  public Object createWorksheet() {
    return this.worksheet;
  }
}
