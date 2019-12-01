package edu.cs3500.spreadsheets.provider;

import java.io.FileReader;
import java.io.IOException;

import edu.cs3500.spreadsheets.controller.Controller;
import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.SpreadSheetModel;
import edu.cs3500.spreadsheets.model.WorksheetBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader;



public class TempMain {
  public static void main(String[] args) {
//    try {
//      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
//              new FileReader("test/test2.gOOD"));
//      IViewModel viewModel = new ViewModel(s);
//      View temp = new EditingView(viewModel);
//      Features controller = new Controller(s, temp);
//      controller.go();
//    } catch (IOException e) {
//      throw new IllegalArgumentException();
//    }
    try {
      SpreadSheetModel s = WorksheetReader.read(new WorksheetBuilder(),
              new FileReader("test/test2.gOOD"));
      IViewModel viewModel = new ViewModel(s);
      View temp = new EditingView(viewModel);
      Features controller = new Controller(s, temp);
      controller.controllerStart();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
}
