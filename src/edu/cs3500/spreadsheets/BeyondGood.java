package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilderClass;
import edu.cs3500.spreadsheets.model.WorksheetReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

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

    System.out.println(args[0]);
    System.out.println(args[1]);
    System.out.println(args[2]);
    System.out.println(args[3]);

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
      WorksheetReader.read(wbc, r);


    }
    else {
      throw new IllegalArgumentException("Invalid Command Line");
    }

    if (args[2].equals("-eval")) {
      System.out.println(w.evaluateCell(w.getCellAt(args[3])));
    }
    else {
      throw new IllegalArgumentException("Invalid Command Line");
    }

    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
  }
}
