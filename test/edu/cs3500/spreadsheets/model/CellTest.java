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

  public void initCells() {
    c1 = new Cell("4");
    c2 = new Cell("true");
    c3 = new Cell("= (SUM 4 5)");
    c4 = new Cell("(SUM 4 5)");
    c5 = new Cell("=(< (SUM 4 5) 10)");
  }

  @Test
  public void evaluateCell() {
    initCells();
    assertEquals(new SNumber(4), c1.evaluateCell());
    assertEquals(new SBoolean(true), c2.evaluateCell());
    assertEquals(new SNumber(9), c3.evaluateCell());
    assertEquals(new SSymbol("(SUM 4 5)"), c4.evaluateCell());
    assertEquals(new SBoolean(true), c5.evaluateCell());
  }
}