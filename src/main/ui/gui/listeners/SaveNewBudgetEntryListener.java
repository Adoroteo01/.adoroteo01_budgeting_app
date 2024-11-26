package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Budget;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;
import ui.gui.GraphicalBudgetingApp;

public class SaveNewBudgetEntryListener implements ActionListener {

    private JTextField idField;
    private JTextField nameField;
    private JTextField budgetAmountField;
    private Budget currentBudget;
    private JFrame parentWindow;
    private GraphicalBudgetingApp app;

    // REQUIRES: budgetAmountField contains a single acceptable double value,
    // idField must contain an id unique from other budget entries in currentBudget.
    // EFECTS: creates new SaveNewBudgetEntryListener with given parentWindow,
    // idField,
    public SaveNewBudgetEntryListener(JFrame parentWindow, JTextField idField, JTextField nameField,
            JTextField budgetAmountField,
            GraphicalBudgetingApp app) {

        this.parentWindow = parentWindow;
        this.idField = idField;
        this.nameField = nameField;
        this.budgetAmountField = budgetAmountField;
        this.app = app;
        this.currentBudget = app.getCurrentBudget();

    }

    @Override
    // REQUIRES: The budget entry that will be created has unique name from existing
    // budget entries in currentBudget. budgetAmount is an acceptable double value.
    // EFFECTS: creates adds new BudgetEntry to currentBudget with given id, name,
    // and budgetAmount.
    public void actionPerformed(ActionEvent e) {

        String id = idField.getText();
        String name = nameField.getText();
        Double budgetAmount = Double.valueOf(budgetAmountField.getText());

        BudgetEntry budgetEntry = new Expense(id, name, budgetAmount);

        currentBudget.addBudgetEntry(budgetEntry);

        app.updateBudgetEntriesList();

        parentWindow.dispose();
    }
}
