package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.IWorksheet;

import java.io.IOException;
import java.util.Set;

public class WorksheetTextualView implements IWorksheetView {

  private IWorksheet model;
  private Appendable ap;

  /**
   * Constructs a Textual View for a Worksheet
   * @param model the model to be passed in
   * @param ap the appendable to be added to
   */
  public WorksheetTextualView(IWorksheet model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void render() throws IOException {
    Set<String> keys = this.model.getAllCellIndices();
    for (String s : keys) {
      this.ap.append(s);
      this.ap.append(" ");
      this.ap.append(this.model.getCellAt(s).getContents());
      this.ap.append("/n");
    }
  }

  @Override
  public void display() {

  }

}
