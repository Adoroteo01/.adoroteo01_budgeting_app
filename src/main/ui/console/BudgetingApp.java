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

    boolean running;

    private String userInput;
    private List<String> userInputs;

    private String currentWindow;
    private Budget currenBudget;

    private Window mainMenu;
    private Window newBudgetMaker;
    private Window budgetOpener;
    private Window budgetViewer;

    private List<Budget> budgets;

    // EFFECTS: creates new budgeting app and runs the app
    public BudgetingApp() {

        mainMenu = new MainMenu();
        newBudgetMaker = new NewBudgetMaker();
        budgetOpener = new BudgetOpener();
        budgetViewer = new BudgetViewer();

        budgets = new ArrayList<Budget>();

        running = true;
        currentWindow = "mainMenu";
        run();
    }

    // EFFECTS: runs the budgeting app
    private void run() {
        while (running) {

            switch (currentWindow) {
                case "mainMenu":
                    mainMenu();
                    break;
                case "budgetOpener":
                    budgetOpener();
                    break;
                case "newBudgetMaker":
                    newBugetMaker();
                    break;
                case "budgetViewer":
                    budgetViewer(currenBudget);
                    break;

            }
        }
    }

    // EFFECTS: Prints main menu to console
    private void mainMenu() {
        mainMenu.open();
        userInput = mainMenu.getUserInput();

        switch (userInput) {
            case "1": // Open Budget
                currentWindow = "budgetOpener";
                break;

            case "2": // New Budget

                currentWindow = "newBudgetMaker";
                break;

            case "3": // Exit Program
                running = false;
                break;

            default:
                System.out.println("Invalid Input... Returning to Main Menu");
                currentWindow = "mainMenu";
                break;
        }
    }

    // EFFECTS: opens the BudgetOpener window
    private void budgetOpener() {
        budgetOpener.set(budgets); // for printing budget names
        budgetOpener.open();

        userInput = budgetOpener.getUserInput();

        for (Budget budget : budgets) {
            if (budget.getName().equals(userInput)) {
                currenBudget = budget;
                break;
            } else {
                currenBudget = null;
            }
        }

        if (currenBudget != null) {
            currentWindow = "budgetViewer";
        } else {
            System.out.println("Budget not found... Returning to main menu");
            currentWindow = "mainMenu";
        }
    }

    // EFFECTS: opens the NewBudgetMaker window
    private void newBugetMaker() {
        newBudgetMaker.open();
        userInputs = newBudgetMaker.getAllInputs();
        currenBudget = makeNewBudget(userInputs.get(0), userInputs.get(1), userInputs.get(2));

        currentWindow = "budgetViewer";
    }

    // EFFECTS: opens the BudgetViewer window
    private void budgetViewer(Budget newBudget) {
        currenBudget = null;
        budgetViewer.set(newBudget);
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

                currentWindow = "mainMenu";
                break;

            default:
                currentWindow = "mainMenu";
                break;
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
