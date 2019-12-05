package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.cs3500.spreadsheets.provider.IViewModel;

public class IViewModelAdapter implements IViewModel {

  private Worksheet worksheet;

  public IViewModelAdapter(Worksheet worksheet) {
    this.worksheet = worksheet;
  }

  /**
   * Return the original contents that the user put into the cell at the given coordinate.
   *
   * @param coordinates the coordinates of the cell
   * @return the users input in the cell at the given coordinate
   */
  @Override
  public String cellOriginalContents(Coord coordinates) {
    int col = coordinates.col;
    int row = coordinates.row;
    return worksheet.getCellAt(col,row).getContents();
  }

  /**
   * Get the evaluated contents of the cell at the given coordinate as a string.
   *
   * @param coord the coordinates of the cell
   * @return the evaluated contents of the cell as a string
   */
  @Override
  public String getEvaluatedContentsAsString(Coord coord) {
    int col = coord.col;
    int row = coord.row;
    String colKey = Coord.colIndexToName(col);
    String fullKey = colKey + row;
    return worksheet.evaluateCell(fullKey).toString();
  }

  /**
   * Returns a Coordinate of the highest column and row in this model.
   *
   * @return a Coord with the highest column and row
   */
  @Override
  public Coord getUpperBound() {
    Set<String> allKeys = this.worksheet.getAllCellIndices();
    int maxCol = 0;
    int maxRow = 0;
    for (String key : allKeys) {
      int col = worksheet.getColumnIndex(key);
      int row = worksheet.getRowIndex(key);
      maxCol = Math.max(col, maxCol);
      maxRow = Math.max(row, maxRow);
    }

    return new Coord(maxCol, maxRow);
  }

  /**
   * Returns a Coordinate of the lowest column and row in this model.
   *
   * @return a Coord with the lowest column and row
   */
  @Override
  public Coord getLowerBound() {
    Set<String> allKeys = this.worksheet.getAllCellIndices();
    List<String> allKeysList = new ArrayList<>();
    allKeysList.addAll(allKeys);
    int minCol = worksheet.getColumnIndex(allKeysList.get(0));
    int minRow = worksheet.getRowIndex(allKeysList.get(0));
    for (String key : allKeys) {
      int col = worksheet.getColumnIndex(key);
      int row = worksheet.getRowIndex(key);
      minCol = Math.min(col, minCol);
      minRow = Math.min(row, minRow);
    }

    return new Coord(minCol, minRow);
  }
}
