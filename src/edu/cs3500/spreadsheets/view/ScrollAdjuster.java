package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * represents a class used for infinite scrolling by knowing when the scrollbar reaches the end and
 * expanding the size of the worksheet.
 */
public class ScrollAdjuster implements AdjustmentListener {

  private IWorksheetView view;

  public ScrollAdjuster(IWorksheetView view) {
    this.view = view;
  }

  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    int max = e.getAdjustable().getMaximum();
    int size = e.getAdjustable().getVisibleAmount();
    int value = e.getAdjustable().getValue();
    int orientation = e.getAdjustable().getOrientation();
    if (max - value <= size) {
      if (orientation == 0) { // Horizontal
        this.view.expand(0, 10);
        e.getAdjustable().setValue(0);
      } else if (orientation == 1) { //Vertical
        this.view.expand(10, 0);
        e.getAdjustable().setValue(0);
      }
    }
  }
}
