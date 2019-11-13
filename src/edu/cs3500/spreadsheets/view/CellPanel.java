package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.*;

public class CellPanel extends JComponent {

  static final int CELL_WIDTH = 80;
  static final int CELL_HEIGHT = 20;

  private String contents;
  private int xPos;
  private int yPos;

  public CellPanel(String contents, int xPos, int yPos) {
    this.contents = contents;
    this.xPos = xPos;
    this.yPos = yPos;
  }

  @Override
  public void paintComponent(Graphics g) {
    FontMetrics fm = g.getFontMetrics();
    g.setColor(Color.BLACK);
    g.drawRect(this.xPos, this.yPos, this.CELL_WIDTH, this.CELL_HEIGHT);
    g.drawString(this.contents,
            this.xPos + this.CELL_WIDTH / 2,
            this.yPos + this.CELL_HEIGHT / 2 + fm.getAscent() / 2);
  }

}
