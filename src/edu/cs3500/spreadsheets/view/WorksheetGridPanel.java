package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;

public class WorksheetGridPanel extends JPanel implements GridPanel {

  private CellPanel[][] worksheet;

  public WorksheetGridPanel (int rows, int cols) {
    this.worksheet = new CellPanel[rows][cols];
    for (int r = 0; r < worksheet.length; r++) {
      for (int c = 0; c < worksheet[r].length; c++) {
        worksheet[r][c] = new CellPanel(
                "", c * CellPanel.CELL_WIDTH, r * CellPanel.CELL_HEIGHT, false);
      }
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    for (CellPanel[] cpArray : worksheet) {
      for (CellPanel cp : cpArray) {
        cp.paintComponent(g);
      }
    }
  }


  @Override
  public void setCell(String contents, int col, int row) {
    this.worksheet[row - 1][col - 1] = new CellPanel(
            contents,
            CellPanel.CELL_WIDTH * (col - 1),
            CellPanel.CELL_HEIGHT * (row - 1), false);
  }
}
