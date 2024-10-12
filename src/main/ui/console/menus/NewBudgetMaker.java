package ui.console.menus;

import java.util.ArrayList;

public class NewBudgetMaker extends Window {

    String newBudgetName;
    String newBudgetStartDate;
    String newBudgetEndDate;

    // EFFECTS: creates new budget maker window with needed inputs
    public NewBudgetMaker() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Enter Budget Name");
        inputs.add("Enter Start Date (yyyy-mm-dd)");
        inputs.add("Enter End Date (yyyy-mm-dd)");
    }

    @Override
    // EFFECTS: prints new budget maker window
    public void open() {
        newPage();
    }

    @Override
    // EFFECTS: nothing
    public void set(Object object) {
    }

}
