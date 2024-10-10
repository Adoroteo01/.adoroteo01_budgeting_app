package ui.console.menus;

import java.util.ArrayList;

public class NewBudgetMaker extends Window{

    String newBudgetName;
    String newBudgetStartDate;
    String newBudgetEndDate;

    public NewBudgetMaker() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Enter Budget Name");
        inputs.add("Enter Start Date (yyyy-mm-dd)");
        inputs.add("Enter End Date (yyyy-mm-dd)");
    }

    @Override
    public void open() {
        newPage();
    }

}
