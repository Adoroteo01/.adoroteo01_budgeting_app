package ui.console;

import java.util.ArrayList;
import java.util.List;

import model.Budget;
import ui.console.menus.BudgetOpener;
import ui.console.menus.BudgetViewer;
import ui.console.menus.MainMenu;
import ui.console.menus.NewBudgetMaker;
import ui.console.menus.Window;

// a budgeting app that allows you to create a budget, track your spending,
// and view a summary of your spending
public class BudgetingApp {

    private String userInput;
    private List<String> userInputs;

    private Window mainMenu;
    private Window newBudgetMaker;
    private BudgetOpener budgetOpener;
    private BudgetViewer budgetViewer;

    private List<Budget> budgets;

    // EFFECTS: creates new budgeting app and runs the app
    public BudgetingApp() {
        mainMenu = new MainMenu();
        newBudgetMaker = new NewBudgetMaker();
        budgetOpener = new BudgetOpener();
        budgetViewer = new BudgetViewer();

        budgets = new ArrayList<Budget>();
        run();
    }

    // EFFECTS: runs the budgeting app
    private void run() {
        mainMenu();
    }

    // EFFECTS: Prints main menu to console
    private void mainMenu() {
        mainMenu.open();
        userInput = mainMenu.getUserInput();

        switch (userInput) {
            case "1": // Open Budget

                budgetOpener();

            case "2": // New Budget

                newBugetMaker();

            default:
                run();
        }
    }

    // EFFECTS: opens the BudgetOpener window
    private void budgetOpener() {
        budgetOpener.setBudgets(budgets);
        budgetOpener.open();

        userInput = budgetOpener.getUserInput();

        Budget targetBudget = null;

        for (Budget budget : budgets) {
            if (budget.getName().equals(userInput)) {
                targetBudget = budget;
                break;
            }
        }

        if (targetBudget != null) {
            budgetViewer(targetBudget);
        } else {
            mainMenu();
        }
    }

    // EFFECTS: opens the NewBudgetMaker window
    private void newBugetMaker() {
        newBudgetMaker.open();
        userInputs = newBudgetMaker.getAllInputs();
        Budget newBudget = makeNewBudget(userInputs.get(0), userInputs.get(1), userInputs.get(2));

        budgetViewer(newBudget);
    }

    // EFFECTS: opens the BudgetViewer window
    private void budgetViewer(Budget newBudget) {
        budgetViewer.setBudget(newBudget);
        budgetViewer.open();

        userInput = budgetViewer.getUserInput();

        switch (userInput) {
            case "1": // TODO: Edit Budget Entries

                break;
            case "2": // TODO: Track

                break;
            case "3": // TODO: Summary

                break;
            case "4": // TODO: Main Menu

                mainMenu();

            default:
                mainMenu();
        }
    }

    // MODIFIES: budgets
    // EFFECTS: adds a new Budget to budgets and returns that new Budget
    public Budget makeNewBudget(String name, String startDate, String endDate) {
        Budget newBudget;
        newBudget = new Budget(name, startDate, endDate);

        budgets.add(newBudget);

        return newBudget;
    }

}
