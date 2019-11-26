package edu.cs3500.spreadsheets.controller;

public interface Features {

  void changeCellContents(String cellKey, String newContents);

  String rejectEdits(String cellKey);

  void changeSelected(String direction);
}
