package edu.cs3500.spreadsheets.view;

/**
 * interface for all header types.
 */
public interface ScrollHeader {

  /**
   * expands the headers by the given amount.
   * @param numToExpand int the number of headers to add
   */
  void expand(int numToExpand);

  /**
   * returns the number of total headers.
   * @return int the number of headers
   */
  int numHeaders();

}
