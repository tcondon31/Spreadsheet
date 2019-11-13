package edu.cs3500.spreadsheets.view;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Represents the JPanel to place in the view, creates the content cells.
 */
public class WorksheetGridPanel extends JPanel implements GridPanel {

  private WorksheetCellPanel[][] worksheet;
  private int selectedRow;
  private int selectedCol;

  /**
   * constructs the GridPanel.
   * @param rows int number of rows long
   * @param cols int number of columns long
   */
  public WorksheetGridPanel (int rows, int cols) {
    this.worksheet = new WorksheetCellPanel[rows][cols];
    this.selectedRow = 0;
    this.selectedCol = 0;
    for (int r = 0; r < worksheet.length; r++) {
      for (int c = 0; c < worksheet[r].length; c++) {
        worksheet[r][c] = new WorksheetCellPanel(
                "",
                c * WorksheetCellPanel.CELL_WIDTH,
                r * WorksheetCellPanel.CELL_HEIGHT,
                false);
      }
    }
    this.worksheet[this.selectedRow][this.selectedCol].select();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (WorksheetCellPanel[] cpArray : worksheet) {
      for (WorksheetCellPanel cp : cpArray) {
        cp.paintComponent(g);
      }
    }
  }


  @Override
  public void setCell(String contents, int col, int row) {
    this.worksheet[row - 1][col - 1] = new WorksheetCellPanel(
            contents,
            WorksheetCellPanel.CELL_WIDTH * (col - 1),
            WorksheetCellPanel.CELL_HEIGHT * (row - 1), false);
  }

  @Override
  public void changeSelected(int row, int col) {
    if (row >= this.worksheet.length || col >= this.worksheet[0].length) {
      throw new IllegalArgumentException("Not on grid");
    }
    WorksheetCellPanel wcp = this.worksheet[this.selectedRow][this.selectedCol];
    wcp.deselect();
    this.selectedRow = row;
    this.selectedCol = col;
    this.worksheet[this.selectedRow][this.selectedCol].select();
  }

  @Override
  public void expand(int numRows, int numCols) {
    WorksheetCellPanel[][] newGrid =
            new WorksheetCellPanel
                    [this.worksheet.length + numRows][this.worksheet[0].length + numCols];
    for (int r = 0; r < this.worksheet.length; r++) {
      for (int c = 0; c < this.worksheet[0].length; c++) {
        newGrid[r][c] = this.worksheet[r][c];
      }
    }
    for (int r = 0; r < newGrid.length; r++) {
      for (int c = 0; c < newGrid[0].length; c++) {
        if (newGrid[r][c] == null) {
          newGrid[r][c] = new WorksheetCellPanel(
                  "",
                  c * WorksheetCellPanel.CELL_WIDTH,
                  r * WorksheetCellPanel.CELL_HEIGHT,
                  false);
        }
      }
    }
    this.worksheet = newGrid;
    this.revalidate();
    this.repaint();
  }
}
