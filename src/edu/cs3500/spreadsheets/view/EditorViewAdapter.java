package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.provider.View;

import java.io.IOException;

public class EditorViewAdapter extends EditableWorksheetFrameView implements IWorksheetView {

  View view;

  public EditorViewAdapter(IWorksheet model, View view) {
    super(model);
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
  public void addFeatures(Features features) {
    this.view.addFeatures(features);
  }
}
