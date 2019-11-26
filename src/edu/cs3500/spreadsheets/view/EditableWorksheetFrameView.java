package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.IWorksheet;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditableWorksheetFrameView extends JFrame implements IWorksheetView {

    private ScrollColumnHeaderPanel columnHeaderPanel;
    private ScrollRowHeaderPanel rowHeaderPanel;
    private WorksheetGridPanel gridPanel;
    private JScrollPane scrollPane;
    private IWorksheet worksheet;

    private BasicEditBarPanel editBarPanel;

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
        this.setMinimumSize(new Dimension(300,300));
        this.setLayout(new BorderLayout());

        this.columnHeaderPanel = new ScrollColumnHeaderPanel(ViewConstants.STARTING_SIZE);
        this.columnHeaderPanel.setPreferredSize(
                new Dimension(ViewConstants.CELL_WIDTH * ViewConstants.STARTING_SIZE,
                        ViewConstants.CELL_HEIGHT));

        this.rowHeaderPanel = new ScrollRowHeaderPanel(ViewConstants.STARTING_SIZE);
        this.rowHeaderPanel.setPreferredSize(
                new Dimension(ViewConstants.CELL_WIDTH,
                        ViewConstants.CELL_HEIGHT * ViewConstants.STARTING_SIZE));

        this.gridPanel = new WorksheetGridPanel(ViewConstants.STARTING_SIZE, ViewConstants.STARTING_SIZE);
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

        this.editBarPanel = new BasicEditBarPanel(ViewConstants.STARTING_SIZE, this.gridPanel);
        this.add(this.editBarPanel, BorderLayout.NORTH);

        CellSelectionListener selection = new CellSelectionListener(this, this.gridPanel, this.editBarPanel);
        this.gridPanel.addMouseListener(selection);

        this.render();
        this.gridPanel.changeSelected(0,0);
        this.editBarPanel.changeTextField(this.getSelectedCellContents());
    }

    /**
     * renders a view of the model.
     *
     * @throws IOException if the appendable cannot be appended onto
     */
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
    public void changeSelected() {

    }

    @Override
    public void addFeatures(Features features) {
        this.editBarPanel.addFeatures(features);
    }

}
