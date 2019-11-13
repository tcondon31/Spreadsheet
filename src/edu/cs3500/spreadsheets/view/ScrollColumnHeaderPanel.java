package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.Coord;

public class ScrollColumnHeaderPanel extends JViewport implements ScrollHeader {

  private final List<CellPanel> columnHeaders;

  public ScrollColumnHeaderPanel(int length) {
    this.columnHeaders = new ArrayList<>();
    for (int i = 1; i <= length; i++) {
      this.columnHeaders.add(new CellPanel(Coord.colIndexToName(i),
              (i - 1) * CellPanel.CELL_WIDTH,
              0, true));
    }
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setOpaque(true);
    setBackground(Color.lightGray);
    for (CellPanel cp : columnHeaders) {
      cp.paintComponent(g);
    }
  }
}
