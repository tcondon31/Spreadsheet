package edu.cs3500.spreadsheets.provider;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Interface for a viewable model. Only provides methods that do not mutate the model, used for
 * viewing purposes.
 */
public interface IViewModel {

  /**
   * Return the original contents that the user put into the cell at the given coordinate.
   *
   * @param coordinates the coordinates of the cell
   * @return the users input in the cell at the given coordinate
   */
  String cellOriginalContents(Coord coordinates);

  /**
   * Get the evaluated contents of the cell at the given coordinate as a string.
   *
   * @param coord the coordinates of the cell
   * @return the evaluated contents of the cell as a string
   */
  String getEvaluatedContentsAsString(Coord coord);

  /**
   * Returns a Coordinate of the highest column and row in this model.
   *
   * @return a Coord with the highest column and row
   */
  Coord getUpperBound();

  /**
   * Returns a Coordinate of the lowest column and row in this model.
   *
   * @return a Coord with the lowest column and row
   */
  Coord getLowerBound();
}
