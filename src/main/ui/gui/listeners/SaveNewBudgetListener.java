package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Budget;

public class SaveNewBudgetListener implements ActionListener {

    private String name;
    private String startDate;
    private String endDate;
    private List<Budget> budgets;

    // EFECTS: Creates SaveNewBudgetListener with given name, startDate, endDate, and budgets
    public SaveNewBudgetListener(String name, String startDate, String endDate, List<Budget> budgets) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgets = budgets;
    }

    @Override
    // EFFECTS: Creates new budget with specified name, startDate, and endDate. Then adds new budget to budgets
    public void actionPerformed(ActionEvent e) {
        System.out.println("New Budget Has Been Made!!!"); // TODO: for testing, remove later

        // TODO: implement making a new budget
        // make new budget
        // add budget to budgets
    }
}
