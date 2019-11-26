package edu.cs3500.spreadsheets.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterButtonListener implements ActionListener {

    private JTextField textField;

    public EnterButtonListener(JTextField textField) {
        this.textField = textField;
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
