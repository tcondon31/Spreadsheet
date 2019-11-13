package edu.cs3500.spreadsheets.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edu.cs3500.spreadsheets.model.Coord;

public class ScrollColumnHeaderPanel extends JPanel implements ScrollHeader {

  private final List<WorksheetCellPanel> columnHeaders;

  public ScrollColumnHeaderPanel(int length) {
    this.columnHeaders = new ArrayList<>();
    for (int i = 1; i <= length; i++) {
      this.columnHeaders.add(new WorksheetCellPanel(Coord.colIndexToName(i),
              (i - 1) * WorksheetCellPanel.CELL_WIDTH,
              0, true));
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (WorksheetCellPanel cp : columnHeaders) {
      cp.paintComponent(g);
    }
  }

  @Override
  public void expand(int numToExpand) {
    int length = this.columnHeaders.size();
    for (int i = length + 1; i <= length + numToExpand; i++) {
      this.columnHeaders.add(new WorksheetCellPanel(Coord.colIndexToName(i),
              (i - 1) * WorksheetCellPanel.CELL_WIDTH,
              0, true));
    }
    this.setPreferredSize(new Dimension(
            this.columnHeaders.size() * WorksheetCellPanel.CELL_WIDTH,
            WorksheetCellPanel.CELL_HEIGHT));
    this.revalidate();
    this.repaint();
  }

  @Override
  public int numHeaders() {
    return this.columnHeaders.size();
  }
}
