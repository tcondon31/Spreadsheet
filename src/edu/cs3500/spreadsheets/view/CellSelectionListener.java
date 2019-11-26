package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.IWorksheet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class designed for representing a selected cell, uses a mouse listener to determine
 * which cell should be selected.
 */
public class CellSelectionListener implements MouseListener {

  private IWorksheetView total;
  private WorksheetGridPanel gridPanel;
  private BasicEditBarPanel editBarPanel;

  /**
   * constructor for the CellSelectionListener class.
   * @param gridPanel WorksheetGridPanel to assign to the class.
   */
  CellSelectionListener(IWorksheetView total, WorksheetGridPanel gridPanel) {
    this.total = total;
    this.gridPanel = gridPanel;
  }

  CellSelectionListener(IWorksheetView total, WorksheetGridPanel gridPanel, BasicEditBarPanel editBarPanel) {
    this.total = total;
    this.gridPanel = gridPanel;
    this.editBarPanel = editBarPanel;
  }

  /**
   * Invoked when the mouse button has been clicked (pressed and released) on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    int xPos = e.getX() / ViewConstants.CELL_WIDTH;
    int yPos = e.getY() / ViewConstants.CELL_HEIGHT;
    try {
      this.gridPanel.changeSelected(yPos,xPos);
      this.total.changeSelected();
      if (this.editBarPanel != null) {
        this.editBarPanel.changeTextField(this.total.getSelectedCellContents());
      }
    }
    catch (IllegalArgumentException iae) {
      // do not want the program to crash with out of bounds click. should do nothing.
    }
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
    // no event to take place.
  }

  /**
   * Invoked when a mouse button has been released on a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    // no event to take place.
  }

  /**
   * Invoked when the mouse enters a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // no event to take place.
  }

  /**
   * Invoked when the mouse exits a component.
   *
   * @param e the event to be processed
   */
  @Override
  public void mouseExited(MouseEvent e) {
    // no event to take place.
  }
}
