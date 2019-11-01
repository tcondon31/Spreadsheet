package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.SBoolean;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SString;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class WorksheetTest {

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
  }

  @Test
  public void createSpreadsheetTest() {
    initCells();
    w1 = new Worksheet();
    assertEquals(new Cell(""), w1.getCellAt("A1"));
    assertEquals(new Cell(""), w1.getCellAt("A3"));
    assertEquals(new Cell(""), w1.getCellAt("B1"));
  }

  @Test
  public void getCellAt() {
    initCells();
    assertEquals(c1, w1.getCellAt("A1"));
    assertEquals(c12, w1.getCellAt("A3"));
    assertEquals(c8, w1.getCellAt("B1"));
  }

  @Test
  public void testGetCellAt() {
    initCells();
    assertEquals(c1, w1.getCellAt(1, 1));
    assertEquals(c12, w1.getCellAt(1, 3));
    assertEquals(c8, w1.getCellAt(2, 1));
  }

  @Test
  public void addCell() {
    initCells();
    assertEquals(new Cell(""), w1.getCellAt(1, 6));
    w1.addCell(1, 6, c7);
    assertEquals(c7, w1.getCellAt(1, 6));
  }

  @Test
  public void evaluateCell() {
    initCells();
    assertEquals(new SNumber(4), w1.evaluateCell("A1"));
    assertEquals(new SNumber(9), w1.evaluateCell("A2"));
    assertEquals(new SNumber(43), w1.evaluateCell("A3"));
    assertEquals(new SSymbol(""), w1.evaluateCell("A10"));
    assertEquals(new SBoolean(true), w1.evaluateCell("A11"));
    assertEquals(new SString("cheese30.0"), w1.evaluateCell("B1"));
    assertEquals(new SBoolean(false), w1.evaluateCell("A5"));
    assertEquals(new SNumber(8), w1.evaluateCell("A12"));
    assertEquals(new SNumber(20), w1.evaluateCell("A13"));
    assertEquals(new SNumber(70), w1.evaluateCell("A4"));
    assertEquals(new SNumber(0), w1.evaluateCell("A14"));
  }

  @Test (expected= IllegalArgumentException.class)
  public void evaluateCyclic1() {
    w1.evaluateCell("A7");
  }

  @Test (expected= IllegalArgumentException.class)
  public void evaluateCyclic2() {
    w1.evaluateCell("A8");
  }

  @Test
  public void isValidName() {
    initCells();
    assertEquals(false, w1.isValidName("A1S"));
    assertEquals(false, w1.isValidName("hamburger"));
    assertEquals(false, w1.isValidName("1A"));
    assertEquals(true, w1.isValidName("A1"));
    assertEquals(true, w1.isValidName("HAMBURGER173"));
  }

  @Test
  public void getAllReferences() {
    initCells();
    assertEquals(new ArrayList<SSymbol>(Arrays.asList(new SSymbol("A1"), new SSymbol("A2"))),
        w1.getAllReferences(new Coord(1, 1), new Coord(1, 2)));
    assertEquals(new ArrayList<SSymbol>(Arrays.asList(new SSymbol("A1"), new SSymbol("A2"),
        new SSymbol("B1"), new SSymbol("B2"))),
        w1.getAllReferences(new Coord(1, 1), new Coord(2, 2)));

  }

  @Test
  public void getListOfReferences() {
    initCells();
    assertEquals(new HashSet<String>(), w1.getListOfReferences(c1));
    assertEquals(new HashSet<String>(Arrays.asList("A1", "A2")), w1.getListOfReferences(c12));
    assertEquals(new HashSet<String>(Arrays.asList("A1", "A2", "A3")), w1.getListOfReferences(c13));
  }

  @Test
  public void getLoRAcc() {
    initCells();
    assertEquals(new HashSet<String>(), w1.getLoRAcc(c1, new ArrayList<>()));
    assertEquals(new HashSet<String>(Arrays.asList("A1", "A2")), w1.getLoRAcc(c12, new ArrayList<>()));
    assertEquals(new HashSet<String>(Arrays.asList("A1", "A2", "A3")), w1.getLoRAcc(c13, new ArrayList<>()));
  }

  @Test
  public void containsKey() {
    initCells();
    assertEquals(true, w1.containsKey("A1"));
    assertEquals(false, w1.containsKey("C7"));
  }
}