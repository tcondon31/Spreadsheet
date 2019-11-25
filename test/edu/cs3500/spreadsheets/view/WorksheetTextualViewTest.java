package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.IWorksheet;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilderClass;
import edu.cs3500.spreadsheets.model.WorksheetCell;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests the textual view for a spreadsheet.
 */
public class WorksheetTextualViewTest {

  WorksheetCell c1;
  WorksheetCell c2;
  WorksheetCell c3;
  WorksheetCell c4;
  WorksheetCell c5;
  WorksheetCell c6;
  WorksheetCell c7;
  WorksheetCell c8;
  WorksheetCell c9;
  WorksheetCell c10;
  WorksheetCell c11;
  WorksheetCell c12;
  WorksheetCell c13;
  WorksheetCell c14;
  WorksheetCell c15;
  WorksheetCell c16;
  WorksheetCell c17;
  WorksheetCell c18;
  IWorksheet w1;
  Appendable sb;

  @Before
  public void initCells() {
    c1 = new Cell("4");
    c2 = new Cell("true");
    c3 = new Cell("= (SUM 4 5)");
    c4 = new Cell("(SUM 4 5)");
    c5 = new Cell("=(< (SUM A1:A3) (PRODUCT 10 2))");
    c6 = new Cell("=(< 10 \"hello\")");
    c7 = new Cell("=(PRODUCT cheese 5)");
    c8 = new Cell("=(CONCAT \"cheese\" (PRODUCT 6 5))");
    c9 = new Cell("=(CHEESE \"cheese\" (PRODUCT 6 5))");
    c10 = new Cell("=(\"CHEESE\" 5 (PRODUCT 6 5))");
    c11 = new Cell("=(SUM 5 true)");
    c12 = new Cell("=(SUM A1 A2 (PRODUCT 5 6))");
    c13 = new Cell("=(SUM A1:A3 (PRODUCT 7 2))");
    c14 = new Cell("=A7");
    c15 = new Cell("=A9");
    c16 = new Cell("=A8");
    c17 = new Cell("= (SUM A1 A1)");
    c18 = new Cell("= (PRODUCT 5 4)");
    w1 = new Worksheet();
    w1.addCell(1, 1, c1);
    w1.addCell(1, 2, c3);
    w1.addCell(1, 3, c12);
    w1.addCell(1, 4, c13);
    w1.addCell(1, 5, c5);
    w1.addCell(2, 1, c8);
    w1.addCell(1, 7, c14);
    w1.addCell(1, 8, c15);
    w1.addCell(1, 9, c16);
    w1.addCell(1, 11, c2);
    w1.addCell(1, 12, c17);
    w1.addCell(1, 13, c18);
    w1.addCell(1, 14, c7);
    sb = new StringBuilder();
  }

  @Test
  public void testRender() throws IOException {
    IWorksheetView view = new WorksheetTextualView(w1, sb);
    view.render();
    Readable r = new StringReader(sb.toString());
    IWorksheet w2 = new Worksheet();
    WorksheetReader.read(new WorksheetBuilderClass(w2), r);
    assertEquals(w2.evaluateCell("A1"), w1.evaluateCell("A1"));
    assertEquals(w2.evaluateCell("A14"), w1.evaluateCell("A14"));
    assertEquals(w2.evaluateCell("B1"), w1.evaluateCell("B1"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNull1() {
    IWorksheetView view = new WorksheetTextualView(null, sb);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNull2() {
    IWorksheetView view = new WorksheetTextualView(w1, null);
  }
}