package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.controller.ProviderFeaturesAdapter;
import edu.cs3500.spreadsheets.provider.MouseListener;
import edu.cs3500.spreadsheets.provider.View;

import java.io.IOException;

public class EditorViewAdapter implements IWorksheetView {

  private View view;

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
    // this is taken care of in the draw function of their visual panel
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
    edu.cs3500.spreadsheets.provider.Features controllerAdapter
            = new ProviderFeaturesAdapter(features);
    this.view.addFeatures(controllerAdapter);
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
