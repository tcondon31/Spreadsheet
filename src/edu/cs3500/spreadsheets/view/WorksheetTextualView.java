package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.IWorksheet;

import java.util.Set;

/**
 * Creates a textual view of the given model.
 */
public class WorksheetTextualView implements IWorksheetView {

  private IWorksheet model;
  private Appendable ap;

  /**
   * Constructs a Textual View for a Worksheet.
   *
   * @param model the model to be passed in
   * @param ap    the appendable to be added to
   */
  public WorksheetTextualView(IWorksheet model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Cannot make model or appendable null");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() {
    Set<String> keys = this.model.getAllCellIndices();
    for (String s : keys) {
      try {
        this.ap.append(s);
        this.ap.append(" ");
        this.ap.append(this.model.getCellAt(s).getContents());
        this.ap.append("\n");
      } catch (Exception e) {
        // do not want to do anything
      }

    }
  }

  @Override
  public void display() {
    // nothing to implement here.
  }

  @Override
  public String getSelectedCellContents() {
    return "";
  }

  @Override
  public void changeSelected(int up, int right) {
    // nothing to implement here.
  }

  @Override
  public void addFeatures(Features features) {
    // nothing to implement here.
  }

  @Override
  public void expand(int numRows, int numCols) {
    // nothing to implement here.
  }

  @Override
  public void resetFocus() {
    // nothing to implement here.
  }

  @Override
  public void repaintImmediately() {
    //nothing to implement
  }

  @Override
  public void updateTextField() {
    // nothing to implement
  }
}
