package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ScrollRowHeaderPanel extends JViewport implements ScrollHeader {

  private final List<CellPanel> rowHeaders;

  public ScrollRowHeaderPanel(int length) {
    this.rowHeaders = new ArrayList<>();
    for (int i = 1; i <= length; i++) {
      this.rowHeaders.add(
              new CellPanel(Integer.toString(i), 0, (i - 1) * CellPanel.CELL_HEIGHT, true));
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setOpaque(true);
    setBackground(Color.lightGray);
    for (CellPanel cp : rowHeaders) {
      cp.paintComponent(g);
    }
  }
}
