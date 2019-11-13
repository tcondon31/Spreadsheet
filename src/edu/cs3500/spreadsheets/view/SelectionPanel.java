package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;

public class SelectionPanel extends JPanel {

  private int xPos;
  private int yPos;

  public SelectionPanel(int xPos, int yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setOpaque(false);
    g.setColor(Color.BLUE);
    g.draw3DRect(this.xPos, this.yPos, CellPanel.CELL_WIDTH, CellPanel.CELL_HEIGHT, true);
  }
}
