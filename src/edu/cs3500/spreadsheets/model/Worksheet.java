package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

public class Worksheet {

  private HashMap<String, Cell> sheet;

  public Worksheet() {
    this.sheet = new HashMap<>();
  }
}
