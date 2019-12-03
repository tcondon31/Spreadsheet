package edu.cs3500.spreadsheets.provider;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import edu.cs3500.spreadsheets.provider.Features;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A visual representation of a spreadsheet in gui form that allows user interaction.
 */
public class EditingView extends JFrame implements View {

  private VisualPanel viewPanel;
  private JButton confirm;
  private JButton clear;
  private JButton save;
  private JTextField editingArea;
  private JPanel editingPanel = new JPanel();
  private IViewModel model;

  /**
   * Constructs an EditingView. Initializes the model, viewPanel, and mouselistener.
   *
   * @param model a read only spreadsheet model from which this view will render itself
   */
  public EditingView(IViewModel model) {
    this.model = model;
    viewPanel = new VisualPanel(model);
    this.addEditingPanel();
    this.acceptMouseListener(new MouseListener(viewPanel.getCellWidth(),
            viewPanel.getCellHeight(), viewPanel.getCellsAsJLabels(),
            this.editingArea, viewPanel.model));
  }

  /**
   * Initializes the visualized representation of the view.
   *
   * @throws IOException if problems with appending to appendable or faultily formed readable
   */
  @Override
  public void render() throws IOException {
    viewPanel.draw();
    this.add(viewPanel);
    this.add(editingPanel, BorderLayout.NORTH);
    this.setVisible(true);
    this.pack();
  }


  /**
   * Add the editing panel, which is a panel that allows user interaction. This uses swing to make
   * a text area with confirm and clear buttons.
   */
  private void addEditingPanel() {

    editingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    confirm = new JButton("Confirm");
    clear = new JButton("Reject");
    save = new JButton("Save");
    editingArea = new JTextField(59);

    editingPanel.add(confirm);
    editingPanel.add(clear);
    editingPanel.add(editingArea);
    editingPanel.add(save);
  }

  /**
   * Adds functionality to the buttons through the features interface. Add the functionality of
   * rejecting the user inputted changes to the clear button, and adds the functionality of editing
   * the model contents according to the user input in the confirm button.
   *
   * @param features features functionality
   */
  @Override
  public void addFeatures(Features features) {
    clear.addActionListener(evt -> editingArea.setText(this.model.cellOriginalContents(new
            Coord(MouseListener.SELECTED_COLUMN + viewPanel.getOffsetCol(),
            MouseListener.SELECTED_ROW + viewPanel.getOffsetRow()))));
    confirm.addActionListener(evt -> features.editModel(MouseListener.SELECTED_COLUMN
            + viewPanel.getOffsetCol(), MouseListener.SELECTED_ROW
            + viewPanel.getOffsetRow(), editingArea));
    save.addActionListener(evt -> features.saveSpreadsheet());
  }

  /**
   * Adds the given mouse listener to the grid of cells.
   *
   * @param m a mouse listener
   */
  @Override
  public void acceptMouseListener(MouseListener m) {
    viewPanel.getCellGrid().addMouseListener(m);
  }
}
