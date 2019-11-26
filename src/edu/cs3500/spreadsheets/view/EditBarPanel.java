package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.controller.Features;

public interface EditBarPanel {

    void changeTextField(String contents);

    void expand(int numCellsToExpand);

    void addFeatures(Features features);

}
