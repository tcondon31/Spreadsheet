package edu.cs3500.spreadsheets.view;

public interface GridPanel {

  void setCell(String contents, int row, int col);

  void changeSelected(int row, int col);

  void expand(int numRows, int numCols);

}
