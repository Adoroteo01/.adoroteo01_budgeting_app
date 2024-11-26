package ui.gui.listeners;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Budget;
import ui.gui.GraphicalBudgetingApp;

public class CreateNewTrackerEntryListener implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    GraphicalBudgetingApp app;
    Budget currentBudget;

    // EFFECTS: creates new CreateNewTrackerEntryListener with given associated app
    // and app's currentBudget
    public CreateNewTrackerEntryListener(GraphicalBudgetingApp app) {

        this.app = app;
        currentBudget = app.getCurrentBudget();

    }

    @Override
    // REQUIRES: ID inputed is a unique id for all budget entries in currentBudget.
    // Amount is a number input.
    // EFFECTS: opens budget entry maker window
    public void actionPerformed(ActionEvent e) {

        // Creating Window
        JFrame createBudgetEntryWindow = new JFrame();
        createBudgetEntryWindow.setSize(WIDTH, HEIGHT);
        createBudgetEntryWindow.setLayout(new GridLayout(4, 2));
        createBudgetEntryWindow.setResizable(false);

        // Create input fields
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField();

        JLabel budgetEntryLabel = new JLabel("Budget Entry: ");
        JTextField budgetEntryField = new JTextField();

        JLabel amountLabel = new JLabel("Amount: ");
        JTextField amountField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(
                new SaveNewTrackerEntryListener(createBudgetEntryWindow, dateField, budgetEntryField, amountField,
                        app));

        // Add components to the frame
        createBudgetEntryWindow.add(dateLabel);
        createBudgetEntryWindow.add(dateField);
        createBudgetEntryWindow.add(budgetEntryLabel);
        createBudgetEntryWindow.add(budgetEntryField);
        createBudgetEntryWindow.add(amountLabel);
        createBudgetEntryWindow.add(amountField);
        createBudgetEntryWindow.add(createButton);

        createBudgetEntryWindow.setVisible(true);
    }

}
