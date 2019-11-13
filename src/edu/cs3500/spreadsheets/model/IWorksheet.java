package edu.cs3500.spreadsheets.model;

import java.util.List;
import java.util.Set;

import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;

/**
 * Interface representing a Worksheet.
 */
public interface IWorksheet {

  /**
   * gets the cell at the given key in the worksheet.
   * @param key   string representation of key to get cell from
   * @return      cell at given key
   */
  WorksheetCell getCellAt(String key);

  /**
   * gets the cell at the given coordinate in the worksheet.
   * @param col   column of cell
   * @param row   row of cell
   * @return      cell at given coordinate
   */
  WorksheetCell getCellAt(int col, int row);

  /**
   * adds a cell that contains a value at the given coordinate in the worksheet.
   * @param col   column of where to add cell
   * @param row   row of where to add cell
   * @param c     Cell to be added
   */
  void addCell(int col, int row, WorksheetCell c);

  /**
   * Returns an Sexp containing the evaluation of the cell based on its contents.
   * @param key the key to be evaluated
   * @return Sexp the evaluated contents in an S-expression
   */
  Sexp evaluateCell(String key);

  /**
   * Evaluates all cells in a worksheet.
   * @return a list of the values of all the evaluated cells
   */
  List<Sexp> evaluateAllCells();

  /**
   * returns true if the given string is a valid name for a cell.
   * @param cell the string to be evaluated
   * @return boolean whether or not the String name is a valid cell name
   */
  boolean isValidName(String cell);

  /**
   * finds all references a cell makes to other cells.
   * @param c the Cell to be checked
   * @return the list of all references
   */
  Set<String> getListOfReferences(WorksheetCell c, List<String> list);

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

  /**
   * returns a set of all keys in the worksheet
   * @return the set of keys as strings
   */
  Set<String> getAllCellIndices();

  /**
   * gets the index number of the column in the key.
   * @param key hashmap key
   * @return the index number of the key
   */
  int getColumnIndex(String key);

  /**
   * gets the row number in the key.
   * @param key the hashmap key that contains the row index
   * @return the row index of the key
   */
  int getRowIndex(String key);

}
