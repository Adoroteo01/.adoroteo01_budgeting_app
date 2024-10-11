package model;

import java.util.ArrayList;
import java.util.List;

// A spending tracker that holds TrackerEntries for it's
// corrisponding Budgeter
public class Tracker {

    private List<TrackerEntry> entries;

    // creates a new Tracker with no entries in list
    public Tracker() {
        entries = new ArrayList<TrackerEntry>();
    }

    public List<TrackerEntry> getEntries() {
        return entries;
    }

    // add entry to entries.
    protected void addEntry(TrackerEntry entry) {
        entries.add(entry);
    }

}
