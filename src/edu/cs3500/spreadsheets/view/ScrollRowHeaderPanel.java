package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * represents the headers for the rows.
 */
public class ScrollRowHeaderPanel extends JPanel implements ScrollHeader {

  private final List<WorksheetCellPanel> rowHeaders;

  /**
   * cosntructor for the ScrollRowHeaderPanel class.
   * @param length int rows to add
   */
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
    this.setPreferredSize(new Dimension(
            WorksheetCellPanel.CELL_WIDTH,
            this.rowHeaders.size() * WorksheetCellPanel.CELL_HEIGHT));
    this.revalidate();
    this.repaint();
  }

  @Override
  public int numHeaders() {
    return this.rowHeaders.size();
  }
}
