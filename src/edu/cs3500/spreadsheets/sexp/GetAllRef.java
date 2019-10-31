package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.*;

import java.util.List;

/**
 * Function object designed to get the list of all references within a S-Expression.
 */
public class GetAllRef implements Func<Sexp, List<String>>, SexpVisitor<List<String>> {

  private Worksheet worksheet;
  private List<String> list;

  /**
   * Constructor for the function object
   * @param w the worksheet to be referenced
   * @param list the list of references to be passed along and added to
   */
  public GetAllRef(Worksheet w, List<String> list) {
    this.worksheet = w;
    this.list = list;
  }

  @Override
  public List<String> apply(Sexp argument) {
    return argument.accept(this);
  }

  @Override
  public List<String> visitBoolean(boolean b) {
    return this.list;
  }

  @Override
  public List<String> visitNumber(double d) {
    return this.list;
  }

  @Override
  public List<String> visitSList(List<Sexp> l) {
    for (Sexp sexp : l) {
      this.list.addAll(new GetAllRef(this.worksheet, this.list).apply(sexp));
    }
    return list;
  }

  @Override
  public List<String> visitSymbol(String s) {
    if (this.worksheet.isValidName(s)) {
      Cell c = this.worksheet.getCellAt(s);
      if (!this.list.contains(s)) {
        this.list.add(s);
        return this.worksheet.getLoRAcc(c, this.list);
      }
    }
    return this.list;
  }

  @Override
  public List<String> visitString(String s) {
    return this.list;
  }
}
