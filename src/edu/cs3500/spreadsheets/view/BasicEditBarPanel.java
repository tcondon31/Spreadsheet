package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents the top bar of a view. Contains JButtons with abilities and a text box.
 */
public class BasicEditBarPanel extends JPanel implements EditBarPanel {

  private int size;
  private JButton enter;
  private JButton cancel;
  private JTextField textField;
  private WorksheetGridPanel gridPanel;

  /**
   * Constructs a BasicEditBarPanel.
   *
   * @param size      the size of the bar
   * @param gridPanel the gridPanel it assists with
   */
  public BasicEditBarPanel(int size, WorksheetGridPanel gridPanel) {
    this.gridPanel = gridPanel;
    this.size = size;
    this.enter = new JButton("✔");
    this.cancel = new JButton("✘");
    this.textField = new JTextField();
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.setPreferredSize(
            new Dimension(this.size * ViewConstants.CELL_WIDTH, 30));
    this.textField.setPreferredSize(new Dimension(200, ViewConstants.CELL_HEIGHT));
    this.enter.setPreferredSize(new Dimension(20, 20));
    this.cancel.setPreferredSize(new Dimension(20, 20));
    this.add(this.enter);
    this.add(this.cancel);
    this.add(this.textField);
  }

  @Override
  public void changeTextField(String contents) {
    this.textField.setText(contents);
  }

  @Override
  public void expand(int numCellsToExpand) {
    int currentWidth = this.getPreferredSize().width;
    this.setPreferredSize(
            new Dimension(currentWidth + ViewConstants.CELL_WIDTH * numCellsToExpand, 30));
  }

  @Override
  public void addFeatures(Features features) {
    this.enter.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String newCellContents = textField.getText();
        String selectedCell = gridPanel.getSelectedCellKey();
        try {
          features.changeCellContents(selectedCell, newCellContents);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        features.resetFocus();
      }
    });
    this.cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedCell = gridPanel.getSelectedCellKey();
        String original = features.rejectEdits(selectedCell);
        textField.setText(original);
        features.resetFocus();
      }
    });
  }
}
