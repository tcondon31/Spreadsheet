package edu.cs3500.spreadsheets.provider;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents an unmodifiable spreadsheet view that incorporates a grid of cells
 * populated by a given read-only Spreadsheet model and has a set of JButtons that allows infinite
 * user traversal of the grid.
 */
public class VisualPanel extends JPanel implements ActionListener {
  protected IViewModel model;
  private static final int GRIDSIZE = 15;
  protected static int offsetRow;
  protected static int offsetCol;
  private JButton right;
  private JButton rightJump;
  private JButton left;
  private JButton leftJump;
  private JButton up;
  private JButton upJump;
  private JButton down;
  private JButton downJump;
  private Border border = BorderFactory.createLineBorder(Color.BLACK);
  private JPanel cellGrid = new JPanel();
  private JPanel view = new JPanel();
  private JPanel navigationPanel = new JPanel();
  private int cellWidth;
  private int cellHeight;
  private ArrayList<ArrayList<JLabel>> cells;


  /**
   * Creates a visual view. Initialized the offset in both the rows and columns.
   *
   * @param model The model to be represented visually in this view
   */
  public VisualPanel(IViewModel model) {
    cells = new ArrayList<>();
    this.model = model;
    offsetRow = 0;
    offsetCol = 0;
    this.cellWidth = 50;
    this.cellHeight = 25;

    view.setLayout(new BorderLayout(0, 0));
    this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    cellGrid.setLayout(new GridLayout(GRIDSIZE + offsetRow + 1,
            GRIDSIZE + offsetCol + 1));

    this.createNavPanel();
    this.createGrid();

    cellGrid.setPreferredSize(new Dimension(cellWidth * (GRIDSIZE + offsetRow + 1),
            cellHeight * (GRIDSIZE + offsetRow + 1)));

    view.add(cellGrid, BorderLayout.CENTER);
    view.add(navigationPanel, BorderLayout.EAST);
    this.add(view);
  }


  /**
   * Render the representation of this panel as a spreadsheet in gui form.
   */
  protected void draw() {
    cellGrid.removeAll();
    this.createGrid();
    view.add(cellGrid, BorderLayout.CENTER);
    cellGrid.revalidate();
    view.repaint();
    this.setVisible(true);
  }


  /**
   * Creates a JPanel with a set of JButtons that change the offsets of the view. Allows the user to
   * move up down, left and right.
   */
  private void createNavPanel() {

    navigationPanel.setLayout(new GridLayout(5, 2));

    right = new JButton("Right");
    rightJump = new JButton("Right " + GRIDSIZE);
    right.addActionListener(this);
    rightJump.addActionListener(this);

    left = new JButton("Left");
    leftJump = new JButton("Left " + GRIDSIZE);
    left.addActionListener(this);
    leftJump.addActionListener(this);

    up = new JButton("Up");
    upJump = new JButton("Up " + GRIDSIZE);
    up.addActionListener(this);
    upJump.addActionListener(this);

    down = new JButton("Down");
    downJump = new JButton("Down " + GRIDSIZE);
    down.addActionListener(this);
    downJump.addActionListener(this);

    JPanel titleNavPanel = new JPanel();
    JLabel titleNavLabel1 = new JLabel("Navigation");
    titleNavPanel.add(titleNavLabel1);

    JPanel titleNavPanel2 = new JPanel();
    JLabel titleNavLabel2 = new JLabel("Panel");
    titleNavPanel2.add(titleNavLabel2);

    navigationPanel.add(titleNavPanel);
    navigationPanel.add(titleNavPanel2);
    navigationPanel.add(right);
    navigationPanel.add(rightJump);
    navigationPanel.add(left);
    navigationPanel.add(leftJump);
    navigationPanel.add(up);
    navigationPanel.add(upJump);
    navigationPanel.add(down);
    navigationPanel.add(downJump);
  }

  /**
   * Creates the grid representation of the model. Origin at the offsets until the specified grid
   * size. Populates the grid with the evaluated values of the cells visibly present within the
   * bounds of the grid.
   */
  private void createGrid() {
    cells.clear();
    for (int row = offsetRow; row <= GRIDSIZE + offsetRow; row++) {
      ArrayList<JLabel> listRow = new ArrayList<>();
      for (int col = offsetCol; col <= GRIDSIZE + offsetCol; col++) {
        JPanel j = new JPanel();
        JLabel jl;
        j.setBorder(border);
        j.setPreferredSize(new Dimension(50, 25));
        if (row == offsetRow && col == offsetCol) {
          jl = new JLabel();
          jl.setBorder(border);
          jl.setOpaque(true);
          jl.setBackground(Color.gray);
          cellGrid.add(j.add(jl));
          listRow.add(jl);
        } else if (row == offsetRow) {
          jl = new JLabel(Coord.colIndexToName(col));
          jl.setBorder(border);
          jl.setOpaque(true);
          jl.setBackground(Color.gray);
          cellGrid.add(j.add(jl));
          listRow.add(jl);
        } else if (col == offsetCol) {
          jl = new JLabel(Integer.toString(row));
          jl.setBorder(border);
          jl.setOpaque(true);
          jl.setBackground(Color.gray);
          cellGrid.add(j.add(jl));
          listRow.add(jl);
        } else {
          Coord coord = new Coord(col, row);
          String contents;
          try {
            contents = this.model.getEvaluatedContentsAsString(coord);
          } catch (Exception e) {
            contents = "#ERROR";
          }
          jl = new JLabel(contents);
          jl.setBorder(border);
          jl.setBackground(Color.white);
          jl.setOpaque(true);
          cellGrid.add(j.add(jl));
          listRow.add(jl);
        }
      }
      cells.add(listRow);
    }
  }

  /**
   * Return a 2D list of cells in the grid as JLabels.
   * @return a 2D list of cells as JLabels
   */
  protected ArrayList<ArrayList<JLabel>> getCellsAsJLabels() {
    return this.cells;
  }

  /**
   * Return the cell grid that  is a JPanel representing the grid of cells in the spreadsheet.
   * @return the cell grid
   */
  protected JPanel getCellGrid() {
    return this.cellGrid;
  }

  /**
   * Returns the width of a cell in the grid.
   * @return the width of the cell
   */
  public int getCellWidth() {
    return cellWidth;
  }

  /**
   * Returns the height of the cell in the grid.
   * @return the height of the cell
   */
  public int getCellHeight() {
    return cellHeight;
  }

  /**
   * Returns the offset of the row, how much the grid is off the origin vertically, up or down.
   * @return the offset of the row, how much the grid is off the origin vertically, up or down
   */
  protected int getOffsetRow() { return offsetRow;}

  /**
   * Returns the offset of the column, how much the grid is off the origin horizontally, left or
   * right.
   * @return the offset of the row, how much the grid is off the origin horizontally, left or right
   */
  protected int getOffsetCol() { return offsetCol;}


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(right)) {
      offsetCol++;
    }

    if (e.getSource().equals(rightJump)) {
      offsetCol += GRIDSIZE;
    }

    if (e.getSource().equals(left)) {
      if (offsetCol > 0) {
        offsetCol--;
      }
    }

    if (e.getSource().equals(leftJump)) {
      if (offsetCol >= GRIDSIZE) {
        offsetCol -= GRIDSIZE;
      }
    }

    if (e.getSource().equals(down)) {
      offsetRow++;
    }

    if (e.getSource().equals(downJump)) {
      offsetRow += GRIDSIZE;
    }

    if (e.getSource().equals(up)) {
      if (offsetRow > 0) {
        offsetRow--;
      }
    }

    if (e.getSource().equals(upJump)) {
      if (offsetRow >= GRIDSIZE) {
        offsetRow -= GRIDSIZE;
      }
    }

    cellGrid.removeAll();
    this.createGrid();
    view.add(cellGrid, BorderLayout.CENTER);
    cellGrid.revalidate();
    view.repaint();
  }

}
