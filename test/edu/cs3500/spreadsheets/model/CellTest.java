package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//TODO: fix when writing tests
/**
 * represents tests for cells.
 */
public class CellTest {

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
  Worksheet w1;

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
    w1 = new Worksheet();
    w1.addCell(1, 1, c1);
    w1.addCell(1, 2, c3);
    w1.addCell(1, 3, c12);
    w1.addCell(1, 4, c13);
    w1.addCell(1, 5, c5);
    w1.addCell(2, 1, c8);

  }

  @Test
  public void evaluateCellTest() {
    //assertEquals(new SNumber(4), c1.evaluateCell());
    //assertEquals(new SBoolean(true), c2.evaluateCell());
    //assertEquals(new SNumber(9), c3.evaluateCell());
    //assertEquals(new SString("(SUM 4 5)"), c4.evaluateCell());
    assertEquals(new SBoolean(false), w1.evaluateCell(new Coord(1, 5).toString()));
    //TODO: Not sure if we want the slashes but thats how toString for SString works
    assertEquals(new SString("cheese30.0"), w1.evaluateCell(new Coord(2, 1).toString()));
    //assertEquals(new Cell("4"), w1.getCellAt(1, 1));
    assertEquals(new SNumber(43), w1.evaluateCell(new Coord(1, 3).toString()));
    //assertEquals("43.0", w1.evaluateCell(w1.getCellAt(1, 3)).toString());
    assertEquals(new SNumber(70.0), w1.evaluateCell(new Coord(1, 4).toString()));
  }
}