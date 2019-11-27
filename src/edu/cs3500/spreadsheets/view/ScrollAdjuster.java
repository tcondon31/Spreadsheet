package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

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
      switch (orientation) {
        case 0: //Horizontal
          this.view.expand(0, 10);
          e.getAdjustable().setValue(0);
          break;
        case 1: //Vertical
          this.view.expand(10, 0);
          e.getAdjustable().setValue(0);
          break;
      }
    }
  }
}
