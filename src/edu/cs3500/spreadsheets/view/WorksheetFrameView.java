package edu.cs3500.spreadsheets.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import edu.cs3500.spreadsheets.model.IWorksheet;

/**
 * Represents the GUI view of an IWorksheet.
 */
public class WorksheetFrameView extends JFrame implements IWorksheetView {

  private final int STARTING_SIZE = 10;

  private ScrollColumnHeaderPanel columnHeaderPanel;
  private ScrollRowHeaderPanel rowHeaderPanel;
  private WorksheetGridPanel gridPanel;
  private JScrollPane scrollPane;
  private IWorksheet worksheet;

  /**
   * constructs a GUI view.
   * @param worksheet IWorksheet to base the view off of
   */
  public WorksheetFrameView(IWorksheet worksheet) {
    super("EXCEL");

    this.worksheet = worksheet;

    setSize(1200, 700);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.setMinimumSize(new Dimension(300,300));
    this.setLayout(new BorderLayout());

    this.columnHeaderPanel = new ScrollColumnHeaderPanel(STARTING_SIZE);
    this.columnHeaderPanel.setPreferredSize(
            new Dimension(WorksheetCellPanel.CELL_WIDTH * STARTING_SIZE,
                WorksheetCellPanel.CELL_HEIGHT));

    this.rowHeaderPanel = new ScrollRowHeaderPanel(STARTING_SIZE);
    this.rowHeaderPanel.setPreferredSize(
            new Dimension(WorksheetCellPanel.CELL_WIDTH,
                WorksheetCellPanel.CELL_HEIGHT * STARTING_SIZE));

    this.gridPanel = new WorksheetGridPanel(STARTING_SIZE, STARTING_SIZE);
    this.gridPanel.setPreferredSize(new Dimension(
            WorksheetCellPanel.CELL_WIDTH * STARTING_SIZE,
            WorksheetCellPanel.CELL_HEIGHT * STARTING_SIZE));

    this.scrollPane = new JScrollPane(this.gridPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.scrollPane.setColumnHeaderView(this.columnHeaderPanel);
    this.scrollPane.setRowHeaderView(this.rowHeaderPanel);
    this.add(this.scrollPane, BorderLayout.CENTER);

    CellSelectionListener selection = new CellSelectionListener(this.gridPanel);
    this.gridPanel.addMouseListener(selection);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void render() throws IOException {
    List<String> allKeys = new ArrayList<>();
    allKeys.addAll(this.worksheet.getAllCellIndices());

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
          this.columnHeaderPanel.numHeaders() * WorksheetCellPanel.CELL_WIDTH,
          this.rowHeaderPanel.numHeaders() * WorksheetCellPanel.CELL_HEIGHT));
      this.scrollPane.revalidate();
      this.scrollPane.repaint();
      try {
        cell = this.worksheet.evaluateCell(key).toString();
      }
      catch (Exception e) {
        cell = "#VALUE!";
      }
      this.gridPanel.setCell(
          cell,
          this.worksheet.getColumnIndex(key),
          this.worksheet.getRowIndex(key));
    }

    this.gridPanel.changeSelected(0,0);
  }

}
