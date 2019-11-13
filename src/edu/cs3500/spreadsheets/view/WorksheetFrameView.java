package edu.cs3500.spreadsheets.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetCell;
import edu.cs3500.spreadsheets.sexp.Sexp;

public class WorksheetFrameView extends JFrame implements IWorksheetView {

  private final int STARTING_SIZE = 120;

  private ScrollColumnHeaderPanel columnHeaderPanel;
  private ScrollRowHeaderPanel rowHeaderPanel;
  private WorksheetGridPanel gridPanel;
  private JScrollPane scrollPane;
  private Worksheet worksheet;

  public WorksheetFrameView(Worksheet worksheet) {
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
            new Dimension(CellPanel.CELL_WIDTH * STARTING_SIZE, CellPanel.CELL_HEIGHT));

    this.rowHeaderPanel = new ScrollRowHeaderPanel(STARTING_SIZE);
    this.rowHeaderPanel.setPreferredSize(
            new Dimension(CellPanel.CELL_WIDTH, CellPanel.CELL_HEIGHT * STARTING_SIZE));

    this.gridPanel = new WorksheetGridPanel(STARTING_SIZE, STARTING_SIZE);
    this.gridPanel.setPreferredSize(new Dimension(
            CellPanel.CELL_WIDTH * STARTING_SIZE,
            CellPanel.CELL_HEIGHT * STARTING_SIZE));

    this.scrollPane = new JScrollPane(
            this.gridPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.scrollPane.setColumnHeaderView(this.columnHeaderPanel);
    this.scrollPane.setRowHeaderView(this.rowHeaderPanel);
    this.add(this.scrollPane);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void render() throws IOException {
    List<String> allKeys = new ArrayList<>();
    allKeys.addAll(this.worksheet.getAllCellIndices());
    //List<Sexp> cells = this.worksheet.evaluateAllCells();

    for (int i = 0; i < allKeys.size(); i++) {
      String cell;
      String key = allKeys.get(i);
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
  }

}
