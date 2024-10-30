package ui.console;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.Budget;
import model.Budgeter;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;
import model.exceptions.InvalidBudgetEntryException;
import persistence.JsonHandler;
import ui.console.menus.BudgetEntryMaker;
import ui.console.menus.BudgetOpener;
import ui.console.menus.BudgetViewer;
import ui.console.menus.MainMenu;
import ui.console.menus.NewBudgetMaker;
import ui.console.menus.SaveWindow;
import ui.console.menus.SummaryWindow;
import ui.console.menus.TrackerEntryMaker;
import ui.console.menus.TrackingWindow;
import ui.console.menus.Window;

// a budgeting app that allows you to create a budget, track your spending,
// and view a summary of your spending
public class BudgetingApp {

    boolean running;

    private JsonHandler jsonHandler;

    private String userInput;
    private List<String> userInputs;

    private String currentWindow;
    private Budget currenBudget;

    private Window mainMenu;
    private Window newBudgetMaker;
    private Window budgetOpener;
    private Window budgetViewer;
    private Window trackingWindow;
    private Window trackerEntryMaker;
    private Window budgetEntryMaker;
    private Window summaryWindow;
    private Window saveWindow;

    private List<Budget> budgets;

    // EFFECTS: creates new budgeting app and runs the app
    public BudgetingApp() {

        jsonHandler = new JsonHandler();

        mainMenu = new MainMenu();
        newBudgetMaker = new NewBudgetMaker();
        budgetOpener = new BudgetOpener();
        budgetViewer = new BudgetViewer();
        trackingWindow = new TrackingWindow();
        trackerEntryMaker = new TrackerEntryMaker();
        budgetEntryMaker = new BudgetEntryMaker();
        summaryWindow = new SummaryWindow();
        saveWindow = new SaveWindow();

        budgets = new ArrayList<Budget>();

        running = true;
        currentWindow = "mainMenu";
        run();
    }

    @SuppressWarnings("methodlength") // cannot split up into more methods
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
                case "trackingWindow":
                    trackingWindow();
                    break;
                case "trackerEntryMaker":
                    trackerEntryMaker();
                    break;
                case "budgetEntryMaker":
                    budgetEntryMaker();
                    break;
                case "summaryWindow":
                    summaryWindow();
                    break;
                case "saveWindow":
                    saveWindow();
                    break;
                case "loadWindow":
                    loadWindow();
                    break;

            }
        }
    }

    // EFFECTS: opens app loading window, loads the app state from file and prints
    // if loading was
    // succesful or not.
    private void loadWindow() {
        // TODO:
        /*
         * budgets = JsonHandler.load("../data/save.json")
         */
    }

    // EFFECTS: opens app saving window, writes the app state to file, and prints if
    // saving was successful or not
    private void saveWindow() {
        saveWindow.open();

        userInputs = saveWindow.getAllInputs();

        try {
            jsonHandler.writeBudgetsToFile(budgets, userInputs.get(1), userInputs.get(0));
            System.out.println("File Saved!\nReturning to Main Menu...");
            currentWindow = "mainMenu";
        } catch (FileNotFoundException e) {
            System.out.println("Saving failed...\nReturning to Main Menu...");
            currentWindow = "mainMenu";
        }
    }

    // EFFECTS: opens summary window
    private void summaryWindow() {
        summaryWindow.set(currenBudget.getBudgeter().rankSpending());

        summaryWindow.open();
        userInput = summaryWindow.getUserInput();

        switch (userInput) {
            case "1":
                currentWindow = "budgetViewer";
                break;

            default:
                break;
        }

    }

    // EFFECTS: opens budget entry maker window and gets required inputs to make
    // a new budget entry
    private void budgetEntryMaker() {
        budgetEntryMaker.open();
        userInputs = budgetEntryMaker.getAllInputs();

        try {
            BudgetEntry budgetEntry = new Expense(userInputs.get(0), userInputs.get(1),
                    Double.valueOf(userInputs.get(2)));
            currenBudget.addBudgetEntry(budgetEntry);
            currentWindow = "budgetViewer";

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount... try again...");
        }

    }

    // EFFECTS: opens tracking window
    private void trackingWindow() {
        trackingWindow.set(currenBudget.getTracker());
        trackingWindow.open();

        userInput = trackingWindow.getUserInput();

        switch (userInput) {
            case "1": // New entry

                currentWindow = "trackerEntryMaker";
                break;

            case "2": // Back

                currentWindow = "budgetViewer";
                break;

            default:

                System.out.println("Invalid input... returning to Budget Viewer");
                currentWindow = "budgetViewer";
                break;
        }

    }

    // EFFECTS: prints tracker entry window and gets inputs needed to make
    // new TrackerEntry
    private void trackerEntryMaker() {
        trackerEntryMaker.open();
        userInputs = trackerEntryMaker.getAllInputs();

        Budgeter budgeter = currenBudget.getBudgeter();
        BudgetEntry budgetEntry = budgeter.findEntry(userInputs.get(1));

        if (userInputs.get(1).equals("")) { // go back to tracking window if no budgetEntry was entered

            System.out.println("Entry was not added... ");
            currentWindow = "trackingWindow";

        } else if (budgetEntry != null) {

            try {
                currenBudget.addTrackerEntry(userInputs.get(0), userInputs.get(1), Double.valueOf(userInputs.get(2)));
                currentWindow = "trackingWindow";

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount... try again...");

            } catch (InvalidBudgetEntryException e) {
                System.out.println("That budget entry does not exist... try again...");

            }

        } else {
            System.out.println("That budget entry does not exist... try again...");
        }
    }

    @SuppressWarnings("methodlength") // cannot split up into more methods
    // EFFECTS: prints main menu to console
    private void mainMenu() {
        currenBudget = null; // resetting currentBudget

        mainMenu.open();
        userInput = mainMenu.getUserInput();

        switch (userInput) {
            case "1": // Open Budget
                currentWindow = "budgetOpener";
                break;

            case "2": // New Budget

                currentWindow = "newBudgetMaker";
                break;

            case "3": // Save
                currentWindow = "saveWindow";
                break;

            case "4": // Load
                currentWindow = "loadWindow";
                break;

            case "5": // Exit Program
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
        budgetViewer.set(newBudget);
        budgetViewer.open();

        userInput = budgetViewer.getUserInput();

        switch (userInput) {
            case "1":
                currentWindow = "budgetEntryMaker";
                break;
            case "2":

                currentWindow = "trackingWindow";
                break;
            case "3":
                currentWindow = "summaryWindow";
                break;
            case "4":

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
