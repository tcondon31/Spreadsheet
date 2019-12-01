package edu.cs3500.spreadsheets.provider;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * This class listens and acts accordingly when a click is registered on the grid,
 * it knows then the selected row and column of the cell. And makes visual changes to the cells
 * when clicked.
 */
public class MouseListener extends MouseAdapter {

  public static int SELECTED_ROW;
  public static int SELECTED_COLUMN;

  private int cellWidth;
  private int cellHeight;
  private ArrayList<ArrayList<JLabel>> cells;
  private JLabel lastClicked;
  private JTextField textArea;
  private IViewModel viewModel;

  /**
   * Constructs a mouse listener.
   * @param cellWidth the width of the cell
   * @param cellHeight the width of the cell
   * @param cells the list of cells in the grid as a 2D list of JLabels
   * @param textArea the text bar that the user types into
   * @param viewModel the read only model
   */
  protected MouseListener(int cellWidth, int cellHeight, ArrayList<ArrayList<JLabel>> cells,
                          JTextField textArea, IViewModel viewModel) {
    this.cellWidth = cellWidth;
    this.cellHeight = cellHeight;
    this.cells = cells;
    this.textArea = textArea;
    this.viewModel = viewModel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    findCellCoord(e.getX(), e.getY()); //get coordinate of the click
    if (SELECTED_COLUMN != 0 && SELECTED_ROW != 0) {

      JLabel j = this.cells.get(SELECTED_ROW).get(SELECTED_COLUMN); //get the cell at that location
      j.setBackground(Color.lightGray);
      j.setOpaque(true);

      textArea.setText(this.viewModel.cellOriginalContents(new Coord(SELECTED_COLUMN
              + VisualPanel.offsetCol, SELECTED_ROW + VisualPanel.offsetRow)));

      if (lastClicked != null) { //if there is a last clicked cell, reset its color
        lastClicked.setBackground(Color.white);
        lastClicked.setOpaque(true);
      }
      this.lastClicked = j;
    }
  }

  /**
   * Sets the selected column and row of the grid based on the x and y coordinate of the click
   * location.
   * @param x the x coordinate on the grid
   * @param y the y coordinate on the grid
   * @throws IllegalArgumentException if x or y are negative
   */
  private void findCellCoord(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Im here");
    } else {
      SELECTED_COLUMN = x / cellWidth;
      SELECTED_ROW = y / cellHeight;
    }
  }

}
