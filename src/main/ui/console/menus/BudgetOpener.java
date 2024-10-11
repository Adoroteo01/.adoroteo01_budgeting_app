package ui.console.menus;

import java.util.List;

import model.Budget;

public class BudgetOpener extends Window {

    List<Budget> budgets;

    @Override
    // REQUIRES: bugets has been set with setBudgets()
    // EFFECTS: prints budget opener window
    public void open() {
        newPage();
        printAvailableBudgets();
        drawDivider();

        System.out.println("Enter budget name: ");
    }

    // REQUIRES: bugets has been set with setBudgets()
    // EFFECTS: prints a list seperated by " | " of all created budget names
    private void printAvailableBudgets() {

        for (Budget budget : budgets) {
            System.out.print(budget.getName() + " | ");
        }
        System.out.println("");
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

}
