package edu.cs3500.spreadsheets.model;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;

public interface IWorksheet {

  /**
   * gets the cell at the given key in the worksheet.
   * @param key   string representation of key to get cell from
   * @return      cell at given key
   */
  Cell getCellAt(String key);

  /**
   * gets the cell at the given coordinate in the worksheet.
   * @param col   column of cell
   * @param row   row of cell
   * @return      cell at given coordinate
   */
  Cell getCellAt(int col, int row);

  /**
   * adds a cell that contains a value at the given coordinate in the worksheet.
   * @param col   column of where to add cell
   * @param row   row of where to add cell
   * @param c     Cell to be added
   */
  void addCell(int col, int row, Cell c);

  /**
   * Returns an Sexp containing the evaluation of the cell based on its contents.
   * @param key the key to be evaluated
   * @return Sexp the evaluated contents in an S-expression
   */
  Sexp evaluateCell(String key);

  /**
   * returns true if the given string is a valid name for a cell.
   * @param cell the string to be evaluated
   * @return boolean whether or not the String name is a valid cell name
   */
  boolean isValidName(String cell);

  /**
   * Returns a list of all cells being referenced within two Coords.
   * @param tl the top left Coord to be evaluated
   * @param br the bottom right Coord to be evaluated
   * @return List of SSymbol the list of symbols that lie within the two bounds
   */
  List<SSymbol> getAllReferences(Coord tl, Coord br);

  /**
   * determines if a worksheet contains the given key.
   * @param key   the key to be searched for
   * @return      true if the key exists, false if not
   */
  boolean containsKey(String key);

}
