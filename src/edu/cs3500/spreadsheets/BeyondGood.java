package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.controller.BasicEditableWorksheetController;
import edu.cs3500.spreadsheets.controller.Features;
import edu.cs3500.spreadsheets.model.IViewModelAdapter;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetBuilderClass;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.provider.EditingView;
import edu.cs3500.spreadsheets.provider.View;
import edu.cs3500.spreadsheets.view.EditableWorksheetFrameView;
import edu.cs3500.spreadsheets.view.EditorViewAdapter;
import edu.cs3500.spreadsheets.view.IWorksheetView;
import edu.cs3500.spreadsheets.view.WorksheetFrameView;
import edu.cs3500.spreadsheets.view.WorksheetTextualView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The main class for our program.
 */
public class BeyondGood {

  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws IOException {

    File f;
    Worksheet w;
    WorksheetReader.WorksheetBuilder wbc;
    Readable r;

    if (args.length == 1) {
      if (args[0].equals("-gui")) {
        IWorksheetView worksheetFrame = new WorksheetFrameView(new Worksheet());
        worksheetFrame.render();
        worksheetFrame.display();
      }
    } else if (args.length > 2) {
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
        } catch (Exception e) {
          System.out.println(e.getMessage());
          return;
        }
      } else {
        throw new IllegalArgumentException("Invalid Command Line");
      }
      if (args.length == 3) {
        if (args[2].equals("-gui")) {
          IWorksheetView worksheetFrame = new WorksheetFrameView(w);
          //worksheetFrame.render();
          worksheetFrame.display();
        } else if (args[2].equals("-edit")) {
          IWorksheetView editableWorksheet = new EditableWorksheetFrameView(w);
          Features controller = new BasicEditableWorksheetController(w, editableWorksheet);
        } else if (args[2].equals("-provider")) {
          View providerView = new EditingView(new IViewModelAdapter(w));
          IWorksheetView ourView = new EditorViewAdapter(providerView);
          Features controller = new BasicEditableWorksheetController(w, ourView);
        } else {
          throw new IllegalArgumentException("Invalid command line");
        }
      } else {
        switch (args[2]) {
          case "-eval":
            String s = "";
            try {
              s = w.evaluateCell(args[3]).toString();
            } catch (Exception e) {
              System.out.print("Error in cell " + args[3] + ": " + e.getMessage());
            }
            try {
              double d = Double.parseDouble(s);
              System.out.print(String.format("%f", d));
            } catch (Exception e) {
              System.out.print(s);
            }
            break;
          case "-save":
            PrintWriter pw = new PrintWriter(args[3]);
            IWorksheetView textualView = new WorksheetTextualView(w, pw);
            textualView.render();
            pw.close();
            break;
          default:
            throw new IllegalArgumentException("Invalid Command Line");
        }
      }
    } else {
      throw new IllegalArgumentException("Invalid Command Line");
    }
  }

}
