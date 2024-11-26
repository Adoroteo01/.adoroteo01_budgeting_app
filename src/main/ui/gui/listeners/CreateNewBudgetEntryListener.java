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

public class CreateNewBudgetEntryListener implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    GraphicalBudgetingApp app;
    Budget currentBudget;

    // EFFECTS: creates new CreateNewBudgetEntryListener with given associated app
    // and app's currentBudget
    public CreateNewBudgetEntryListener(GraphicalBudgetingApp app) {

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
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();

        JLabel budgetAmountLabel = new JLabel("Budget Amount: ");
        JTextField budgetAmountField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(
                new SaveNewBudgetEntryListener(createBudgetEntryWindow, idField, nameField, budgetAmountField, app));

        // Add components to the frame
        createBudgetEntryWindow.add(idLabel);
        createBudgetEntryWindow.add(idField);
        createBudgetEntryWindow.add(nameLabel);
        createBudgetEntryWindow.add(nameField);
        createBudgetEntryWindow.add(budgetAmountLabel);
        createBudgetEntryWindow.add(budgetAmountField);
        createBudgetEntryWindow.add(createButton);

        createBudgetEntryWindow.setVisible(true);
    }

}
