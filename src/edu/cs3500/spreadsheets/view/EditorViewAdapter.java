package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.controller.ProviderFeaturesAdapter;
import edu.cs3500.spreadsheets.provider.View;

import java.io.IOException;

/**
 * Adapter class for our view and the provider's view.
 */
public class EditorViewAdapter implements IWorksheetView {

  private View view;

  /**
   * Constructor for the adapter class.
   * @param view our view to be adapted
   */
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
    //method handled elsewhere, does not need to be called
  }

  @Override
  public void changeSelected(int up, int right) {
    // method is unnecessary, handled elsewhere
  }

  @Override
  public void addFeatures(Features features) {
    edu.cs3500.spreadsheets.provider.Features controllerAdapter
            = new ProviderFeaturesAdapter(features);
    this.view.addFeatures(controllerAdapter);
  }

  @Override
  public void expand(int numRows, int numCols) {
    // method does not need to be implemented, expand handled elsewhere
  }

  @Override
  public void resetFocus() {
    // this method is not used, focus is always set
  }

  @Override
  public void repaintImmediately() {
    // everything is updated as it goes, not necessary to adapt this
  }

  @Override
  public void updateTextField() {
    // text field is updated elsewhere
  }
}
