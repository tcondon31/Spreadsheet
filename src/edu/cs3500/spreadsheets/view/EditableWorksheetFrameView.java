package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.IWorksheet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a view of an IWorksheet that allows for editing and cell selection.
 */
public class EditableWorksheetFrameView extends JFrame implements IWorksheetView {

  private ScrollColumnHeaderPanel columnHeaderPanel;
  private ScrollRowHeaderPanel rowHeaderPanel;
  private WorksheetGridPanel gridPanel;
  private JScrollPane scrollPane;
  private IWorksheet worksheet;

  private BasicEditBarPanel editBarPanel;

  /**
   * Constructs an EditableWorksheetFrameView.
   *
   * @param worksheet the model to base it off of
   */
  public EditableWorksheetFrameView(IWorksheet worksheet) {
    super("GO CRAZY AHH GO STUPID AHH");

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
    this.scrollPane.getVerticalScrollBar().setUnitIncrement(ViewConstants.SCROLL_SPEED);
    this.scrollPane.getHorizontalScrollBar().setUnitIncrement(ViewConstants.SCROLL_SPEED);
    this.add(this.scrollPane, BorderLayout.CENTER);
    AdjustmentListener adjust = new ScrollAdjuster(this);
    this.scrollPane.getVerticalScrollBar().addAdjustmentListener(adjust);
    this.scrollPane.getHorizontalScrollBar().addAdjustmentListener(adjust);

    this.editBarPanel = new BasicEditBarPanel(ViewConstants.STARTING_SIZE, this.gridPanel);
    this.add(this.editBarPanel, BorderLayout.NORTH);

    CellSelectionListener selection =
            new CellSelectionListener(this, this.gridPanel, this.editBarPanel);
    this.gridPanel.addMouseListener(selection);
    this.render();
    this.gridPanel.changeSelected(0, 0);
    this.editBarPanel.changeTextField(this.getSelectedCellContents());
  }

  /**
   * renders a view of the model.
   *
   * @throws IOException if the appendable cannot be appended onto
   */
  @Override
  public void render() throws IOException {
    List<String> allKeys = new ArrayList<>(this.worksheet.getAllCellIndices());

    for (int i = 0; i < allKeys.size(); i++) {
      String cell;
      String key = allKeys.get(i);
      int row = this.worksheet.getRowIndex(key);
      int col = this.worksheet.getColumnIndex(key);
      if (row > this.rowHeaderPanel.numHeaders()) {
        this.expand(row - this.rowHeaderPanel.numHeaders(), 0);
      }
      if (col > this.columnHeaderPanel.numHeaders()) {
        this.expand(0, col - this.columnHeaderPanel.numHeaders());
      }
      this.gridPanel.setPreferredSize(new Dimension(
              this.columnHeaderPanel.numHeaders() * ViewConstants.CELL_WIDTH,
              this.rowHeaderPanel.numHeaders() * ViewConstants.CELL_HEIGHT));
      this.scrollPane.revalidate();
      this.scrollPane.repaint();
      try {
        cell = this.worksheet.evaluateCell(key).toString();
        cell = cell.replaceAll("^\"|\"$", "");
        cell = cell.replace("\\\"", "\"");
      } catch (Exception e) {
        cell = "#VALUE!";
      }
      this.gridPanel.setCell(
              cell,
              this.worksheet.getColumnIndex(key),
              this.worksheet.getRowIndex(key));
    }
    this.updateTextField();
  }

  /**
   * Display this view.
   */
  @Override
  public void display() {
    setVisible(true);
  }

  /**
   * returns the String contents of the Cell that is currently selected.
   *
   * @return the contents of whatever cell is selected
   */
  @Override
  public String getSelectedCellContents() {
    String key = this.gridPanel.getSelectedCellKey();
    return this.worksheet.getCellAt(key).getContents();
  }

  /**
   * updates the Text Field to display the correct contents if applicable to the view.
   */
  @Override
  public void changeSelected(int up, int right) {
    this.gridPanel.changeSelectedBy(up, right);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void addFeatures(Features features) {
    this.editBarPanel.addFeatures(features);
    this.resetFocus();
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // dont need
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          features.changeSelected("up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          features.changeSelected("down");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          features.changeSelected("left");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          features.changeSelected("right");
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                || e.getKeyCode() == KeyEvent.VK_DELETE) {
          features.clearCell(gridPanel.getSelectedCellKey());
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // dont need
      }
    });
  }

  @Override
  public void expand(int numRows, int numCols) {
    this.gridPanel.expand(numRows, numCols);
    this.columnHeaderPanel.expand(numCols);
    this.rowHeaderPanel.expand(numRows);
    this.render();
  }

  @Override
  public void repaintImmediately() {
    this.repaint();
    this.revalidate();
  }

  @Override
  public void updateTextField() {
    this.editBarPanel.changeTextField(this.getSelectedCellContents());
  }


}
