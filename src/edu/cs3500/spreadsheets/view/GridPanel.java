package edu.cs3500.spreadsheets.view;

public interface GridPanel {

  void setCell(String contents, int row, int col);

  void changeSelected(int row, int col);

  WorksheetCellPanel getCell(int row, int col);

}
