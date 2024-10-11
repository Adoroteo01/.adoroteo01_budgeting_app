package ui.console.menus;

import model.Budget;
import model.budgetentries.BudgetEntry;

public class BudgetViewer extends Window {

    Budget budget;

    // EFFECTS: creates a new BudgetViewer with a scanner
    public BudgetViewer() {
        super();
        options = "1) Edit Budget Entries\n2) Track\n3) Summary\n4) Main Menu";

    }

    @Override
    // REQUIRES: budget must have already been given a value with setBudget()
    // EFFECTS: prints BudgetViewer window to console
    public void open() {
        title = budget.getName();

        newPage();
        printTitle();
        System.out.println(budget.getStartDate() + " - " + budget.getEndDate());
        drawDivider();

        printEntries();

        System.out.println("\n");

        printOptions();
    }

    // REQUIRES: budget must have already been given a value with setBudget()
    // EFFECTS: prints all BudgetEntries in budget
    private void printEntries() {
        System.out.println("Budget Entries\n--------------\n");
        for (BudgetEntry budgetEntry : budget.getBudgetEntries()) {
            System.out.println("- " + budgetEntry.getName() + " | " + String.valueOf(budgetEntry.getBudgetAmount())
                    + " | " + String.valueOf(budgetEntry.getActualAmount()));
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: sets this.budget value
    public void set(Object budget) {
        this.budget = (Budget) budget;
    }

}
