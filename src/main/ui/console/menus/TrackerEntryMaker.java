package ui.console.menus;

import java.util.ArrayList;

public class TrackerEntryMaker extends Window {

    // EFFECTS: makes tracker entry maker window with needed inputs
    public TrackerEntryMaker() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Enter entry date");
        inputs.add("Enter budget entry");
        inputs.add("Enter amount");

    }

    @Override
    // EFFECTS: nothing
    public void set(Object object) {
    }

    @Override
    // EFFECTS: prints tracker entry maker window
    public void open() {
        newPage();

    }

}
