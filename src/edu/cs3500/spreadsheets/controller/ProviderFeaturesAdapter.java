package edu.cs3500.spreadsheets.controller;

import java.io.IOException;

import javax.swing.JTextField;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.Features;

/**
 * Adapter class for our Features interface and the provider's Features interface.
 */
public class ProviderFeaturesAdapter implements Features {

  edu.cs3500.spreadsheets.controller.Features ourController;

  /**
   * Constructor for the adapter class.
   * @param ourController our version of the controller to adapt
   */
  public ProviderFeaturesAdapter(edu.cs3500.spreadsheets.controller.Features ourController) {
    this.ourController = ourController;
  }

  /**
   * Changes the contents of the model at the given coordinate. IF the cell already exists, then it
   * updates the contents of the cell with the given contents. If the cell does not exist creates a
   * cell in the model at those coordinates with the given contents.
   *
   * @param col  the column of the cell to be edited
   * @param row  the row of the cell to be edited
   * @param text the contents of the cell to be added/updated
   */
  @Override
  public void editModel(int col, int row, JTextField text) {
    String colKey = Coord.colIndexToName(col);
    String fullKey = new StringBuilder().append(colKey).append(row).toString();
    String textField = text.getText();
    try {
      ourController.changeCellContents(fullKey, textField);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Starts the controller.
   */
  @Override
  public void controllerStart() {
    // no need for this with our controller
  }

  /**
   * Saves the current values of the spreadsheet ot a file.
   */
  @Override
  public void saveSpreadsheet() {
    // we do not have this functionality b/c it was extra credit
  }
}
