package edu.cs3500.spreadsheets.provider;

import java.io.IOException;

import edu.cs3500.spreadsheets.provider.Features;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is a TextualView class, that is a view that represents a model in textual form.
 * Represents a spreadsheet in textual form.
 */
public class TextualView implements View {
  private IViewModel model;
  private Appendable app;

  /**
   * Creates a TextualView.
   *
   * @param model the model to be represented in this view
   * @param app   an appendable to append this views rendering of the model to
   */
  public TextualView(IViewModel model, Appendable app) {
    this.model = model;
    this.app = app;
    if (this.app == null) {
      throw new IllegalArgumentException("Appendable must not be null");
    }
  }

  @Override
  public void render() throws IOException {
    app.append(this.toString());
  }

  @Override
  public void addFeatures(Features features) {}

  @Override
  public void acceptMouseListener(MouseListener m) {

  }

  /**
   * Returns a string representing the current state of the model. The model is a spreadsheet, so
   * returns a worksheet represented as a string, with the names of the cells and the contents they
   * contain.
   *
   * @return a string representing the current state of the model
   */
  @Override
  public String toString() {
    StringBuilder stringWorksheet = new StringBuilder();
    for (int col = model.getLowerBound().col; col <= model.getUpperBound().col; col++) {
      for (int row = model.getLowerBound().row; row <= model.getUpperBound().row; row++) {
        Coord coord = new Coord(col, row);
        if (!model.cellOriginalContents(coord).equals("")) {
          stringWorksheet.append(coord.toString()).append(" ").append(
                  model.cellOriginalContents(coord)).append("\n");
        }
      }
    }
    return stringWorksheet.toString();
  }
}
