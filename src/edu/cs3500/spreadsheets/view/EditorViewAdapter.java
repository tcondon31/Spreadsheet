package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.provider.View;

import java.io.IOException;

public class EditorViewAdapter implements IWorksheetView {

  View view;

  public EditorViewAdapter(View view) {
    if (view == null) {
      throw new IllegalArgumentException("Can not adapt null view");
    }
    this.view = view;
  }

  @Override
  public void render() throws IOException {
    this.view.render();
  }

  @Override
  public void display() {

  }

  @Override
  public String getSelectedCellContents() {
    return null;
  }

  @Override
  public void changeSelected(int up, int right) {

  }

  @Override
  public void addFeatures(Features features) {
    this.view.addFeatures((edu.cs3500.spreadsheets.provider.Features) features);
  }

  @Override
  public void expand(int numRows, int numCols) {

  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void repaintImmediately() {

  }

  @Override
  public void updateTextField() {

  }
}
