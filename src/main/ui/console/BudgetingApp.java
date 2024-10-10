package ui.console;

import java.util.ArrayList;
import java.util.List;

import model.Budgeter;
import ui.console.menus.BudgetOpener;
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
    private Window budgetOpener;

    private List<Budgeter> budgeters;

    public BudgetingApp() {
        mainMenu = new MainMenu();
        newBudgetMaker = new NewBudgetMaker();
        budgetOpener = new BudgetOpener();

        budgeters = new ArrayList<Budgeter>();
        run();
    }

    // EFFECTS: runs the budgeting app
    private void run() {
        mainMenu.open();
        userInput = mainMenu.getUserInput();

        switch (userInput) {
            case "1": // Open Budget

                budgetOpener.open();

            case "2": // New Budget

                newBudgetMaker.open();
                userInputs = newBudgetMaker.getAllInputs();
                makeNewBudget(userInputs.get(0), userInputs.get(1), userInputs.get(2));

            default:
                break;
        }
    }

    // TODO
    public void makeNewBudget(String name, String startDate, String endDate) {
        // stub
    }

}
