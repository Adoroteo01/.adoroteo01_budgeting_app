package ui.console.menus;

import java.util.ArrayList;

public class BudgetEntryMaker extends Window {

    // creates new BudgetEntryMaker with required inputs
    public BudgetEntryMaker() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Enter budget entry name");
        inputs.add("Enter budgeted amount");
    }

    @Override
    // EFFECTS: nothing
    public void set(Object object) {
    }

    @Override
    // EFFECTS: prints budget entry maker window
    public void open() {
        newPage();
    }

}
