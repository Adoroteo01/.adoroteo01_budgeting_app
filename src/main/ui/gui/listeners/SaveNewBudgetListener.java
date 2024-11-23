package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Budget;

public class SaveNewBudgetListener implements ActionListener {

    private JTextField nameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private List<Budget> budgets;
    private JFrame parentWindow;

    // EFECTS: Creates SaveNewBudgetListener with given name, startDate, endDate,
    // and budgets
    public SaveNewBudgetListener(JFrame parentWindow, JTextField nameField, JTextField startDateField,
            JTextField endDateField,
            List<Budget> budgets) {

        this.nameField = nameField;
        this.startDateField = startDateField;
        this.endDateField = endDateField;
        this.budgets = budgets;
        this.parentWindow = parentWindow;
    }

    @Override
    // EFFECTS: Creates new budget with specified name, startDate, and endDate. Then
    // adds new budget to budgets
    public void actionPerformed(ActionEvent e) {
        System.out.println("New Budget Has Been Made!!!"); // TODO: for testing, remove later

        String name = nameField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        Budget budget = new Budget(name, startDate, endDate);

        budgets.add(budget);

        System.out.println(// TODO: For testing, delete later
                "name:" + budget.getName() + "\nstart:" + budget.getStartDate() + "\nend:" + budget.getEndDate());

        parentWindow.dispose();
    }
}
