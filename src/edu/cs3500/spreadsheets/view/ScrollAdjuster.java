package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

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
        try {
          this.view.expand(0, 10);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        e.getAdjustable().setValue(0);
      } else if (orientation == 1) { //Vertical
        try {
          this.view.expand(10, 0);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        e.getAdjustable().setValue(0);
      }
    }
  }
}
