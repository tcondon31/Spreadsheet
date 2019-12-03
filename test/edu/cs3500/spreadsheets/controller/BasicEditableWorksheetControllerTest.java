package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilderClass;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.EditableWorksheetFrameView;
import edu.cs3500.spreadsheets.view.IWorksheetView;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * test class for the methods in the controller.
 */
public class BasicEditableWorksheetControllerTest {

  IWorksheet model;
  IWorksheetView view;
  Features contr;
  File f;
  Readable r;
  WorksheetBuilderClass wbc;

  @Before
  public void setUp() throws Exception {
    model = new Worksheet();
    wbc = new WorksheetBuilderClass(model);
    f = new File("resources/SpreadsheetTest");
    r = new FileReader(f);
    WorksheetReader.read(wbc, r);
    view = new EditableWorksheetFrameView(model);
    contr = new BasicEditableWorksheetController(model, view);
  }

  @Test
  public void changeCellContents() throws IOException {
    assertEquals("4.0", this.model.evaluateCell("A1").toString());
    this.contr.changeCellContents("A1", "hello");
    assertEquals("\"hello\"", this.model.evaluateCell("A1").toString());
  }

  @Test
  public void changeCellContents2() throws IOException {
    assertEquals("", this.model.evaluateCell("D80").toString());
    this.contr.changeCellContents("D80", "=(SUM 4 5)");
    assertEquals("9.0", this.model.evaluateCell("D80").toString());
  }

  @Test
  public void rejectEdits() {
    assertEquals("4.0", this.model.evaluateCell("A1").toString());
    this.contr.rejectEdits("A1");
    assertEquals("4.0", this.model.evaluateCell("A1").toString());
  }

  @Test
  public void rejectEdits2() {
    assertEquals("", this.model.evaluateCell("D80").toString());
    this.contr.rejectEdits("D80");
    assertEquals("", this.model.evaluateCell("D80").toString());
  }

  @Test
  public void changeSelected() {
    assertEquals("4", this.view.getSelectedCellContents());
    this.contr.changeSelected("down");
    assertEquals("=(SUM 4 5)", this.view.getSelectedCellContents());
    this.contr.changeSelected("up");
    assertEquals("4", this.view.getSelectedCellContents());
    this.contr.changeSelected("right");
    assertEquals("=(SUM (PRODUCT 2 3) (SUM 5 6) (PRODUCT 2 4))",
            this.view.getSelectedCellContents());
    this.contr.changeSelected("right");
    this.contr.changeSelected("right");
    assertEquals("", this.view.getSelectedCellContents());
  }

  @Test
  public void changeSelected2() {
    assertEquals("4", this.view.getSelectedCellContents());
    this.contr.changeSelected("left");
    assertEquals("4", this.view.getSelectedCellContents());
    this.contr.changeSelected("up");
    assertEquals("4", this.view.getSelectedCellContents());
  }
}