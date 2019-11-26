package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.view.IWorksheetView;
public class BasicEditableWorksheetController implements Features {

  private IWorksheet worksheet;
  private IWorksheetView view;

  public BasicEditableWorksheetController(IWorksheet worksheet, IWorksheetView view) {
    this.worksheet = worksheet;
    this.view = view;
    this.view.addFeatures(this);
    this.view.display();
  }

  @Override
  public void changeCellContents(String cellKey, String newContents) {
    if (this.worksheet.containsKey(cellKey)) {
      this.worksheet.editCell(cellKey, newContents);
    }
    else {
      this.worksheet.addCell(this.worksheet.getColumnIndex(cellKey),
        this.worksheet.getRowIndex(cellKey),
          new Cell(newContents));
    }
    this.view.render();
  }

  @Override
  public String rejectEdits(String cellKey) {
        return this.view.getSelectedCellContents();
    }

  @Override
  public void changeSelected(String direction) {
    switch (direction) {
      case "up" :
        this.view.changeSelected(1, 0);
      case "down" :
        this.view.changeSelected(-1, 0);
      case "left" :
        this.view.changeSelected(0, -1);
      case "right" :
        this.view.changeSelected(0, 1);
    }
  }
}
