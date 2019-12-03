package edu.cs3500.spreadsheets.provider;

import java.io.IOException;

import edu.cs3500.spreadsheets.provider.Features;

/**
 * Creates a visualization of the worksheet.
 */
public interface View {

  /**
   * Initializes the visualized representation of the view.
   * @throws IOException if problems with appending to appendable or faultily formed readable
   */
  void render() throws IOException;

  /**
   * Accepts a features class that will allow for the features methods to be called when needed
   * in the view. This allows the view to perform the functionality of the features class.
   * @param features a features interface
   */
  void addFeatures(Features features);

  /**
   * Adds a mouseListener to the area where the user interaction occurs.
   * @param m a MouseListener that will listen to events
   */
  void acceptMouseListener(MouseListener m);


}
