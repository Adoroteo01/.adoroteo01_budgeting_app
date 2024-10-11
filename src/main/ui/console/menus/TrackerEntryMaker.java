package ui.console.menus;

import java.util.ArrayList;

public class TrackerEntryMaker extends Window {

    public TrackerEntryMaker() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Enter entry date");
        inputs.add("Enter budget entry");
        inputs.add("Enter amount");

    }

    @Override
    public void set(Object object) {
    }

    @Override
    public void open() {
        newPage();

    }

}
