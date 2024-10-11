package ui.console.menus;

import model.Tracker;
import model.TrackerEntry;

public class TrackingWindow extends Window {
    private Tracker tracker;

    public TrackingWindow() {
        super();
        title = "Tracking";
        options = "1) New entry\n2) Back";

    }

    @Override
    // EFFECTS: sets tracker
    public void set(Object tracker) {
        this.tracker = (Tracker) tracker;
    }

    @Override
    // REQUIRES: tracker is already set with set()
    // EFFECTS: prints tracker window
    public void open() {
        newPage();
        printTitle();
        drawDivider();

        printEntries();

        printOptions();
    }

    // EFFECTS: prints all tracker entries in tracker
    private void printEntries() {
        for (TrackerEntry entry : tracker.getEntries()) {
            System.out.println(
                    " - " + entry.getDate() + " | " + entry.getBudgetEntry().getName() + " | " + entry.getAmount());

        }
    }

}
