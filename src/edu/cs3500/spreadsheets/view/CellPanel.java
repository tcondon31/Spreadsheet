package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class CellPanel extends JComponent {

  static final int CELL_WIDTH = 80;
  static final int CELL_HEIGHT = 20;
  final boolean textCentered;

  private String contents;
  private int xPos;
  private int yPos;

  public CellPanel(String contents, int xPos, int yPos, boolean textCentered) {
    this.contents = contents;
    this.xPos = xPos;
    this.yPos = yPos;
    this.textCentered = textCentered;
  }

  @Override
  public void paintComponent(Graphics g) {
    int textOffset = 0;
    if (this.textCentered) {
      textOffset = this.CELL_WIDTH / 2;
    }
    FontMetrics fm = g.getFontMetrics();
    g.setColor(Color.BLACK);
    g.drawRect(this.xPos, this.yPos, this.CELL_WIDTH, this.CELL_HEIGHT);
    //Rectangle2D r = new Rectangle(this.xPos - 1 , this.yPos - 1, this.CELL_WIDTH + 1, this.CELL_HEIGHT + 1);
    //g.setClip(r);
    g.drawString(this.contents,
        this.xPos + textOffset,
        this.yPos + this.CELL_HEIGHT / 2 + fm.getAscent() / 2);
  }

}
