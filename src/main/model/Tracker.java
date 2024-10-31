package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// A spending tracker that holds TrackerEntries for it's
// corrisponding Budgeter
public class Tracker {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracker tracker = (Tracker) o;
        return Objects.equals(getEntries(), tracker.getEntries());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEntries());
    }

    private List<TrackerEntry> entries;

    // creates a new Tracker with no entries in list
    public Tracker() {
        entries = new ArrayList<TrackerEntry>();
    }

    public List<TrackerEntry> getEntries() {
        return entries;
    }

    // add entry to entries.
    public void addEntry(TrackerEntry entry) {
        entries.add(entry);
    }

}
