package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellSelectionListener implements MouseListener {

  private WorksheetGridPanel gridPanel;

  public CellSelectionListener(WorksheetGridPanel gridPanel) {
    this.gridPanel = gridPanel;
  }

  /**
   * Invoked when the mouse button has been clicked (pressed and released) on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    int xPos = e.getX() / WorksheetCellPanel.CELL_WIDTH;;
    int yPos = e.getY() / WorksheetCellPanel.CELL_HEIGHT;
    this.gridPanel.changeSelected(yPos,xPos);
    this.gridPanel.revalidate();
    this.gridPanel.repaint();
  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mousePressed(MouseEvent e) {

  }

  /**
   * Invoked when a mouse button has been released on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseReleased(MouseEvent e) {

  }

  /**
   * Invoked when the mouse enters a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseEntered(MouseEvent e) {

  }

  /**
   * Invoked when the mouse exits a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseExited(MouseEvent e) {

  }
}
