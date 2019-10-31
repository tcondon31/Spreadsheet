package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CellTest {

  Cell c1;
  Cell c2;
  Cell c3;
  Cell c4;
  Cell c5;
  Cell c6;
  Cell c7;
  Cell c8;
  Cell c9;
  Cell c10;
  Cell c11;
  Cell c12;
  Cell c13;

  public void initCells() {
    c1 = new Cell("4");
    c2 = new Cell("true");
    c3 = new Cell("= (SUM 4 5)");
    c4 = new Cell("(SUM 4 5)");
    c5 = new Cell("=(< (SUM 10 (SUM 2 2)) (PRODUCT 5 5))");
    c6 = new Cell("=(< 10 \"hello\")");
    c7 = new Cell("=(PRODUCT cheese 5)");
    c8 = new Cell("=(CONCAT \"cheese\" (PRODUCT 6 5))");
    c9 = new Cell("=(CHEESE \"cheese\" (PRODUCT 6 5))");
    c10 = new Cell("=(\"CHEESE\" 5 (PRODUCT 6 5))");
    c11 = new Cell("=(SUM 5 true)");
    c12 = new Cell("=(PRODUCT 4 5)");
    c13 = new Cell("=(SUM (PRODUCT 3 4) PRODUCT (SUM 8 2))");
  }

  @Test
  public void evaluateCellTest() {
    initCells();
    assertEquals(new SNumber(4), c1.evaluateCell());
    assertEquals(new SBoolean(true), c2.evaluateCell());
    assertEquals(new SNumber(9), c3.evaluateCell());
    assertEquals(new SString("(SUM 4 5)"), c4.evaluateCell());
    assertEquals(new SBoolean(true), c5.evaluateCell());
    //TODO: Not sure if we want the slashes but thats how toString for SString works
    assertEquals(new SString("\"cheese\"30.0"), c8.evaluateCell());
  }

  @Test
  public void testSumFunc() {
    initCells();
    assertEquals(new SBoolean(true), c5.evaluateCell());
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTestFail1() {
    initCells();
    c6.evaluateCell();
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTestFail2() {
    initCells();
    c7.evaluateCell();
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTestFail3() {
    initCells();
    c9.evaluateCell();
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTestFail4() {
    initCells();
    c10.evaluateCell();
  }

  @Test (expected = IllegalArgumentException.class)
  public void evaluateCellTestFail5() {
    initCells();
    c11.evaluateCell();
  }
}