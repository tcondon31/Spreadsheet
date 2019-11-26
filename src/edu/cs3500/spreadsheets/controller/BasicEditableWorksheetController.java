package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.view.EditableWorksheetFrameView;
import edu.cs3500.spreadsheets.view.IWorksheetView;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicEditableWorksheetController implements Features {

    private IWorksheet worksheet;
    private IWorksheetView view;

    public BasicEditableWorksheetController(IWorksheet worksheet, IWorksheetView view) {
        this.worksheet = worksheet;
        this.view = view;
        this.view.addFeatures(this);
        this.view.display();
    }

    @Override
    public void changeCellContents(String cellKey, String newContents) {
        this.worksheet.editCell(cellKey, newContents);
        this.view.render();
    }
}
