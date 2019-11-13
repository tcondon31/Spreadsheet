package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

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
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    int textOffset = 0;
    if (this.textCentered) {
      textOffset = this.CELL_WIDTH / 2;
    }
    Rectangle2D r = new Rectangle(this.xPos - 1 , this.yPos - 1,
        this.CELL_WIDTH + 1, this.CELL_HEIGHT + 1);
    g2d.setClip(r);
    FontMetrics fm = g.getFontMetrics();
    g2d.setColor(Color.BLACK);
    g2d.drawRect(this.xPos, this.yPos, this.CELL_WIDTH, this.CELL_HEIGHT);
    g2d.drawString(this.contents,
        this.xPos + textOffset,
        this.yPos + this.CELL_HEIGHT / 2 + fm.getAscent() / 2);
  }

}
