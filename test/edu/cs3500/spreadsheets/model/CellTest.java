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

  public void initCells() {
    c1 = new Cell("4");
    c2 = new Cell("true");
    c3 = new Cell("= (SUM 4 5)");
    c4 = new Cell("(SUM 4 5)");
  }

  @Test
  public void parseCell() {
    initCells();
    assertEquals(new SNumber(4), c1.parseCell());
    assertEquals(new SBoolean(true), c2.parseCell());
    assertEquals(new SList(Arrays.asList(new SSymbol("SUM"), new SNumber(4), new SNumber(5))), c3.parseCell());
    assertEquals(new SList(Arrays.asList(new SSymbol("SUM"), new SNumber(4), new SNumber(5))), c4.parseCell());
  }

  @Test
  public void evaluateCell() {
    initCells();
    assertEquals(new SNumber(4), c1.evaluateCell());
    assertEquals(new SBoolean(true), c2.evaluateCell());
    assertEquals(new SNumber(9), c3.evaluateCell());
    assertEquals(new SNumber(9), c4.evaluateCell());
  }
}