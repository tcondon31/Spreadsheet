package edu.cs3500.spreadsheets.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.IWorksheet;

/**
 * Represents the GUI view of an IWorksheet.
 */
public class WorksheetFrameView extends JFrame implements IWorksheetView {

  private ScrollColumnHeaderPanel columnHeaderPanel;
  private ScrollRowHeaderPanel rowHeaderPanel;
  private WorksheetGridPanel gridPanel;
  private JScrollPane scrollPane;
  private IWorksheet worksheet;

  /**
   * constructs a GUI view.
   *
   * @param worksheet IWorksheet to base the view off of
   */
  public WorksheetFrameView(IWorksheet worksheet) throws IOException {
    super("YOU CAN LOOK BUT YOU CANT TOUCH");

    if (worksheet == null) {
      throw new IllegalArgumentException("Cannot render a null worksheet");
    }

    this.worksheet = worksheet;

    setSize(1200, 700);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.setMinimumSize(new Dimension(300, 300));
    this.setLayout(new BorderLayout());

    this.columnHeaderPanel = new ScrollColumnHeaderPanel(ViewConstants.STARTING_SIZE);
    this.columnHeaderPanel.setPreferredSize(
            new Dimension(ViewConstants.CELL_WIDTH * ViewConstants.STARTING_SIZE,
                    ViewConstants.CELL_HEIGHT));

    this.rowHeaderPanel = new ScrollRowHeaderPanel(ViewConstants.STARTING_SIZE);
    this.rowHeaderPanel.setPreferredSize(
            new Dimension(ViewConstants.CELL_WIDTH,
                    ViewConstants.CELL_HEIGHT * ViewConstants.STARTING_SIZE));

    this.gridPanel =
            new WorksheetGridPanel(ViewConstants.STARTING_SIZE, ViewConstants.STARTING_SIZE);
    this.gridPanel.setPreferredSize(new Dimension(
            ViewConstants.CELL_WIDTH * ViewConstants.STARTING_SIZE,
            ViewConstants.CELL_HEIGHT * ViewConstants.STARTING_SIZE));

    this.scrollPane = new JScrollPane(this.gridPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.scrollPane.setColumnHeaderView(this.columnHeaderPanel);
    this.scrollPane.setRowHeaderView(this.rowHeaderPanel);
    this.add(this.scrollPane, BorderLayout.CENTER);

    this.render();
    this.gridPanel.changeSelected(0, 0);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void render() {
    List<String> allKeys = new ArrayList<>(this.worksheet.getAllCellIndices());

    for (int i = 0; i < allKeys.size(); i++) {
      String cell;
      String key = allKeys.get(i);
      int row = this.worksheet.getRowIndex(key);
      int col = this.worksheet.getColumnIndex(key);
      if (row > this.rowHeaderPanel.numHeaders()) {
        this.gridPanel.expand(row - this.rowHeaderPanel.numHeaders(), 0);
        this.rowHeaderPanel.expand(row - this.rowHeaderPanel.numHeaders());
      }
      if (col > this.columnHeaderPanel.numHeaders()) {
        this.gridPanel.expand(0, col - this.columnHeaderPanel.numHeaders());
        this.columnHeaderPanel.expand(col - this.columnHeaderPanel.numHeaders());
      }
      this.gridPanel.setPreferredSize(new Dimension(
              this.columnHeaderPanel.numHeaders() * ViewConstants.CELL_WIDTH,
              this.rowHeaderPanel.numHeaders() * ViewConstants.CELL_HEIGHT));
      this.scrollPane.revalidate();
      this.scrollPane.repaint();
      try {
        cell = this.worksheet.evaluateCell(key).toString();
      } catch (Exception e) {
        cell = "#VALUE!";
      }
      this.gridPanel.setCell(
              cell,
              this.worksheet.getColumnIndex(key),
              this.worksheet.getRowIndex(key));
    }
  }

  @Override
  public String getSelectedCellContents() {
    String key = this.gridPanel.getSelectedCellKey();
    return this.worksheet.getCellAt(key).getContents();
  }

  @Override
  public void changeSelected(int up, int right) {
    // nothing to implement here
  }

  @Override
  public void addFeatures(Features features) {
    // nothing to implement here
  }

  @Override
  public void expand(int numRows, int numCols) {
    // nothing to implement here
  }

  @Override
  public void resetFocus() {
    // not necessary because there are no keyListeners
  }

  @Override
  public void repaintImmediately() {
    this.repaint();
    this.revalidate();
  }

  @Override
  public void updateTextField() {
    // nothing to implement because not editable
  }


}
