package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.CellInterface;
import edu.cs3500.spreadsheets.provider.SpreadSheetModel;
import edu.cs3500.spreadsheets.provider.ValueFormula;

import java.util.Set;

public class ModelAdapter implements SpreadSheetModel{

  IWorksheet w;

  public ModelAdapter(IWorksheet w) {
    this.w = w;
  }


  @Override
  public void createCell(int col, int row, String contents) {
    this.w.addCell(col, row, new Cell(contents));
  }

  @Override
  public void editCellContents(Coord coordinates, String contents) {
    String key = Coord.colIndexToName(coordinates.col);
    key += coordinates.row;
    this.w.editCell(key, contents);
  }

  @Override
  public String cellOriginalContents(Coord coordinates) {
    String key = Coord.colIndexToName(coordinates.col);
    key += coordinates.row;
    return this.w.getCellAt(key).getContents();
  }

  @Override
  public ValueFormula evaluatedContents(Coord cell) {
    //TODO: this
    String key = Coord.colIndexToName(cell.col);
    key += cell.row;
    return null; //this.w.evaluateCell(key);
  }

  @Override
  public String getEvaluatedContentsAsString(Coord coord) {
    String key = Coord.colIndexToName(coord.col);
    key += coord.row;
    return this.w.evaluateCell(key).toString();
  }

  @Override
  public void deleteCellContents(Coord coord) {
    String key = Coord.colIndexToName(coord.col);
    key += coord.row;
    this.w.editCell(key, "");
  }

  @Override
  public CellInterface getCell(Coord coord) {
    String key = Coord.colIndexToName(coord.col);
    key += coord.row;
    CellInterface cell = new CellAdapter(this.w.getCellAt(key));
    return cell;
  }

  @Override
  public Coord getUpperBound() {
    int col = 1;
    int row = 1;
    for (String s : this.w.getAllCellIndices()) {
      int tempCol = this.w.getColumnIndex(s);
      int tempRow = this.w.getRowIndex(s);
      if (tempCol > col) {
        col = tempCol;
      }
      if (tempRow > row) {
        row = tempRow;
      }
    }
    return new Coord(col, row);
  }

  @Override
  public Coord getLowerBound() {
    int col = this.getUpperBound().col;
    int row = this.getUpperBound().row;
    for (String s : this.w.getAllCellIndices()) {
      int tempCol = this.w.getColumnIndex(s);
      int tempRow = this.w.getRowIndex(s);
      if (tempCol < col) {
        col = tempCol;
      }
      if (tempRow < row) {
        row = tempRow;
      }
    }
    return new Coord(col, row);
  }
}
