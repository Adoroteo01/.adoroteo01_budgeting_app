package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Budget;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;
import model.exceptions.InvalidBudgetEntryException;
import ui.gui.GraphicalBudgetingApp;

public class SaveNewTrackerEntryListener implements ActionListener {

    private JTextField dateField;
    private JTextField budgetEntryField;
    private JTextField amountField;
    private Budget currentBudget;
    private JFrame parentWindow;
    private GraphicalBudgetingApp app;

    // REQUIRES: amountField contains a single acceptable double value,
    // budgetEntryField must contain a name of an existing BudgetEntry in
    // currentBudget.
    // EFECTS: creates new SaveNewTrackerEntryListener with given parentWindow,
    // dateField, budgetEntryField, amountField, app, and app's currentBudget
    public SaveNewTrackerEntryListener(JFrame parentWindow, JTextField dateField, JTextField budgetEntryField,
            JTextField amountField,
            GraphicalBudgetingApp app) {

        this.parentWindow = parentWindow;
        this.dateField = dateField;
        this.budgetEntryField = budgetEntryField;
        this.amountField = amountField;
        this.app = app;
        this.currentBudget = app.getCurrentBudget();

    }

    @Override
    // REQUIRES: budgetEntry is a valid existing budget entry name in currentBudget.
    // amount is an acceptable double value.
    // EFFECTS: adds new TrackerEntry to currentBudget with given date, budgetEntry,
    // and amount. then updates the apps visual lists
    public void actionPerformed(ActionEvent e) {

        String date = dateField.getText();
        String budgetEntry = budgetEntryField.getText();
        Double amount = Double.valueOf(amountField.getText());

        try {
            currentBudget.addTrackerEntry(date, budgetEntry, amount);
        } catch (InvalidBudgetEntryException e1) {
            System.out.println("Invalid Budget Entry Name");
        }

        app.updateTrackerEntriesList();
        app.updateBudgetEntriesList();

        parentWindow.dispose();
    }
}
