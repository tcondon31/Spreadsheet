package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.view.IWorksheetView;

import java.io.IOException;

/**
 * Implementation of a controller for an Editable worksheet. Contains methods that are able to alter
 * cells, change the selected cell, and revert cell contents back to normal.
 */
public class BasicEditableWorksheetController implements Features {

  private IWorksheet worksheet;
  private IWorksheetView view;

  /**
   * Constructs a controller.
   *
   * @param worksheet the model to base the controller on
   * @param view      the display of the model
   */
  public BasicEditableWorksheetController(IWorksheet worksheet, IWorksheetView view) {
    this.worksheet = worksheet;
    this.view = view;
    this.view.addFeatures(this);
    try {
      this.view.render();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.view.display();
  }

  @Override
  public void changeCellContents(String cellKey, String newContents) throws IOException {
    if (this.worksheet.containsKey(cellKey)) {
      this.worksheet.editCell(cellKey, newContents);
    } else {
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
      case "up":
        this.view.changeSelected(1, 0);
        break;
      case "down":
        this.view.changeSelected(-1, 0);
        break;
      case "left":
        this.view.changeSelected(0, -1);
        break;
      case "right":
        this.view.changeSelected(0, 1);
        break;
      default:
        // do nothing
        break;
    }
    this.view.updateTextField();
    this.view.repaintImmediately();
  }

  @Override
  public void resetFocus() {
    this.view.resetFocus();
  }

  @Override
  public void clearCell(String cellKey) throws IOException{
    this.worksheet.removeCell(cellKey);
    this.view.render();
    this.view.updateTextField();
    this.view.repaintImmediately();
  }

}
