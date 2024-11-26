package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Budget;
import ui.gui.GraphicalBudgetingApp;

public class SaveNewBudgetListener implements ActionListener {

    private JTextField nameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private List<Budget> budgets;
    private JFrame parentWindow;
    private GraphicalBudgetingApp app;

    // EFECTS: Creates SaveNewBudgetListener with given name, startDate, endDate,
    // app, budgets of the app.
    public SaveNewBudgetListener(JFrame parentWindow, JTextField nameField, JTextField startDateField,
            JTextField endDateField,
            GraphicalBudgetingApp app) {

        this.parentWindow = parentWindow;
        this.nameField = nameField;
        this.startDateField = startDateField;
        this.endDateField = endDateField;
        this.app = app;
        this.budgets = app.getBudgets();

    }

    @Override
    // REQUIRES: The budget that will be created has unique name from existing
    // budgets
    // EFFECTS: Creates new budget with specified name, startDate, and endDate. Then
    // adds new budget to budgets. Then updates the app's graphical budget list.
    // Then closes create new budget menu
    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        Budget budget = new Budget(name, startDate, endDate);

        budgets.add(budget);

        app.updateScrollingList(app.getBudgetsScroller(), app.getBudgetNames());

        parentWindow.dispose();
    }
}
