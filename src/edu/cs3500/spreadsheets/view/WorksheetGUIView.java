package edu.cs3500.spreadsheets.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class WorksheetGUIView extends JFrame implements IWorksheetView {

  private JScrollPane scrollPane;

  public WorksheetGUIView() {
    super();
    this.setTitle("Spreadsheet");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new GridLayout());
    turtlePanel = new TurtlePanel();
    turtlePanel.setPreferredSize(new Dimension(500, 500));
    scrollPane = new JScrollPane(turtlePanel);
    this.add(scrollPane, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //input textfield
    input = new JTextField(15);
    buttonPanel.add(input);

    //buttons
    commandButton = new JButton("Execute");
    commandButton.addActionListener((ActionEvent e) ->
    {
      if (commandCallback != null) { //if there is a command callback
        commandCallback.accept(input.getText()); //send command to be processed
        input.setText(""); //clear the input text field
      }
    });
    buttonPanel.add(commandButton);

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);

    commandCallback = null;

    this.pack();


  }

  @Override
  public void render() {

  }

}
