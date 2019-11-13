package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ScrollRowHeaderPanel extends JPanel implements ScrollHeader {

  private final List<WorksheetCellPanel> rowHeaders;

  public ScrollRowHeaderPanel(int length) {
    this.rowHeaders = new ArrayList<>();
    for (int i = 1; i <= length; i++) {
      this.rowHeaders.add(
              new WorksheetCellPanel(Integer.toString(i),
                      0,
                      (i - 1) * WorksheetCellPanel.CELL_HEIGHT,
                      true));
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setOpaque(true);
    setBackground(Color.lightGray);
    for (WorksheetCellPanel cp : rowHeaders) {
      cp.paintComponent(g);
    }
  }

  @Override
  public void expand(int numToExpand) {
    int length = this.rowHeaders.size();
    for (int i = length + 1; i <= length + numToExpand; i++) {
      this.rowHeaders.add(
              new WorksheetCellPanel(Integer.toString(i),
                      0,
                      (i - 1) * WorksheetCellPanel.CELL_HEIGHT,
                      true));
    }
  }
}
