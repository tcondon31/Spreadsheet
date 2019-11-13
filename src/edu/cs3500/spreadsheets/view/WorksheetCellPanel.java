package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class WorksheetCellPanel extends JPanel implements CellPanel {

  static final int CELL_WIDTH = 80;
  static final int CELL_HEIGHT = 20;
  private final boolean header;

  private boolean selected;
  private String contents;
  private int xPos;
  private int yPos;

  public WorksheetCellPanel(String contents, int xPos, int yPos, boolean header) {
    this.contents = contents;
    this.xPos = xPos;
    this.yPos = yPos;
    this.header = header;
    this.selected = false;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    Color fillColor = Color.WHITE;
    int textOffset = 0;
    if (this.header) {
      textOffset = this.CELL_WIDTH / 2;
      fillColor = Color.LIGHT_GRAY;
    }
    Rectangle2D r = new Rectangle(this.xPos - 1 , this.yPos - 1,
        this.CELL_WIDTH + 1, this.CELL_HEIGHT + 1);
    g2d.setClip(r);
    FontMetrics fm = g.getFontMetrics();
    g2d.setColor(fillColor);
    g2d.fillRect(this.xPos, this.yPos, this.CELL_WIDTH, this.CELL_HEIGHT);
    if (this.selected) {
      g2d.setColor(Color.BLUE);
      g2d.setStroke(new BasicStroke(3));
    }
    else {
      g2d.setColor(Color.BLACK);
      g2d.setStroke(new BasicStroke(1));
    }
    g2d.drawRect(this.xPos, this.yPos, this.CELL_WIDTH, this.CELL_HEIGHT);
    g2d.setColor(Color.BLACK);
    g2d.drawString(this.contents,
        this.xPos + textOffset,
        this.yPos + this.CELL_HEIGHT / 2 + fm.getAscent() / 2);
  }

  @Override
  public void select() {
    this.selected = true;
  }

  @Override
  public void deselect() {
    this.selected = false;
  }
}
