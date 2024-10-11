package ui.console.menus;

import java.util.List;

import model.budgetentries.BudgetEntry;

public class SummaryWindow extends Window {

    List<BudgetEntry> entries;

    public SummaryWindow() {
        super();
        title = "Summary";
        options = "1) Back";
    }

    @Override
    // EFFECTS: sets budgeter
    public void set(Object entries) {
        this.entries = (List<BudgetEntry>) entries;
    }

    @Override
    // REQUIRES: budgeter must have already been set
    // EFFECTS: prints tracking summary
    public void open() {
        newPage();
        printTitle();
        drawDivider();

        System.out.println("Highest Spending Areas\n----------------------");

        for (BudgetEntry entry : entries) {
            System.out.println("- " + entry.getName() + " | " + String.valueOf(entry.getActualAmount()));
        }

        printOptions();
    }

}
