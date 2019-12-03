package edu.cs3500.spreadsheets.provider;

import java.util.Objects;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;


/**
 * An adaptor that represents a viewable model, a SpreadSheetModel that is for viewing purposes,
 * and does not have public methods that allow for mutating the model.
 */
public class ViewModel implements IViewModel {
  private SpreadSheetModel model;

  /**
   * Constructs a ViewModel
   * @param model a model that this view model is representing as a read only model
   */
  public ViewModel(SpreadSheetModel model) {
    this.model = model;
  }

  @Override
  public String cellOriginalContents(Coord coordinates) {
    return this.model.cellOriginalContents(coordinates);
  }

  @Override
  public String getEvaluatedContentsAsString(Coord coord) {
    return this.model.getEvaluatedContentsAsString(coord);
  }

  @Override
  public Coord getUpperBound() {
    return model.getUpperBound();
  }

  @Override
  public Coord getLowerBound() {
    return model.getLowerBound();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewModel m = (ViewModel) o;
    return this.model.equals(m.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.model);
  }

}
