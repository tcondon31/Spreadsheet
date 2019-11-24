package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.IWorksheet;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class WorksheetFrameControllerView extends WorksheetFrameView {

  private JTextField editBar;

  /**
   * constructs a GUI view.
   *
   * @param worksheet IWorksheet to base the view off of
   */
  public WorksheetFrameControllerView(IWorksheet worksheet) throws IOException {
    super(worksheet);
    this.editBar = new JTextField(this.getSelectedCellContents());
    this.editBar.setPreferredSize(new Dimension(200, 30));
    JButton submit = new JButton("Submit");
    JButton clear = new JButton("Clear");
    JPanel topBar = new JPanel();
    topBar.add(submit);
    topBar.add(clear);
    topBar.add(this.editBar);
    this.add(topBar, BorderLayout.NORTH);
    CellSelectionListener selection = new CellSelectionListener(this, this.gridPanel);
    this.gridPanel.addMouseListener(selection);
  }

  @Override
  public void changeSelected() {
    this.editBar.setText(this.getSelectedCellContents());
  }


}
