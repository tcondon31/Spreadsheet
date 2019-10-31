package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilderClass;
import edu.cs3500.spreadsheets.model.WorksheetReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The main class for our program.
 */
public class BeyondGood {

  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {

    File f;
    Worksheet w;
    WorksheetReader.WorksheetBuilder wbc;
    Readable r;

    if (args[0].equals("-in")) {
      f = new File(args[1]);
      w = new Worksheet();
      wbc = new WorksheetBuilderClass(w);

      try {
        r = new FileReader(f);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        r = null;
      }
      try {
        WorksheetReader.read(wbc, r);
      }
      catch (Exception e) {
        throw new IllegalArgumentException("Could not read cells correctly");
      }
    }
    else {
      throw new IllegalArgumentException("Invalid Command Line");
    }

    if (args[2].equals("-eval")) {
      String s = "";
      try {
        s = w.evaluateCell(w.getCellAt(args[3])).toString();
      }
      catch (Exception e) {
        System.out.print("Error in cell " + args[3]);
      }
      try {
        double d = Double.parseDouble(s);
        System.out.print(String.format("%f", d));
      }
      catch (Exception e) {
        System.out.print(s);
      }
    }
    else {
      throw new IllegalArgumentException("Invalid Command Line");
    }
  }
}
