package edu.cs3500.spreadsheets.view;

import java.io.IOException;

/**
 * Interface for any type of view. Creates a visual representation of the model.
 */
public interface IWorksheetView {

  /**
   * renders a view of the model.
   * @throws IOException if the appendable cannot be appended onto
   */
  void render() throws IOException;

  /**
   * Display this view.
   */
  void display();

}
