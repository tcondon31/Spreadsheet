package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.SpreadSheetModel;
import edu.cs3500.spreadsheets.sexp.Sexp;

import java.util.List;
import java.util.Set;

public class ModelAdapter extends Worksheet implements IWorksheet{

  SpreadSheetModel ivm;

  public ModelAdapter(SpreadSheetModel ivm) {
    super();
    this.ivm = ivm;
  }

  @Override
  public WorksheetCell getCellAt(String key) {
    return null;
  }

  @Override
  public WorksheetCell getCellAt(int col, int row) {
    return null;
  }

  @Override
  public void addCell(int col, int row, WorksheetCell c) {
    String contents = c.getContents();
    this.ivm.createCell(col, row, contents);
  }

  @Override
  public Sexp evaluateCell(String key) {
    return null;
  }

  @Override
  public List<Sexp> evaluateAllCells() {
    return null;
  }

  @Override
  public boolean isValidName(String cell) {
    return false;
  }

  @Override
  public Set<String> getListOfReferences(WorksheetCell c, List<String> list) {
    return null;
  }

  @Override
  public List<Sexp> getAllReferences(Coord tl, Coord br) {
    return null;
  }

  @Override
  public boolean containsKey(String key) {
    return false;
  }

  @Override
  public Set<String> getAllCellIndices() {
    return null;
  }

  @Override
  public int getColumnIndex(String key) {
    return 0;
  }

  @Override
  public int getRowIndex(String key) {
    return 0;
  }

  @Override
  public void editCell(String key, String contents) {
    int row = this.getRowIndex(key);
    int col = this.getColumnIndex(key);
    this.ivm.editCellContents(new Coord(col, row), contents);
  }

  @Override
  public void removeCell(String key) {

  }

  @Override
  public int getNumCells() {
    return 0;
  }
}
