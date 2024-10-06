package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.budgetentries.BudgetEntry;

// A budget tracker that has a map of budget entries
public class Budgeter {
    private HashMap<String, BudgetEntry> budgetEntries;

    // EFFECTS: creates a new budgeter with no budget entries
    public Budgeter() {
        budgetEntries = new HashMap<String, BudgetEntry>();
    }

    // EFFECTS: returns a collection of all the entries in budgetEntries
    public Collection<BudgetEntry> getbudgetEntries() {
        return budgetEntries.values();
    }

    // EFFECTS: clears budgetEntries
    public void clear() {
        budgetEntries.clear();
    }

    // REQUIRES: BudgetEntry name and object cannot already be in HashMap
    // MODIFIES: this
    // EFFECTS: adds budgetEntry to budgetEntries with a key the
    // same as the budgetEntry name
    public void addEntry(BudgetEntry budgetEntry) {

        budgetEntries.put(budgetEntry.getName(), budgetEntry);
    }

    // MODIFIES: this
    // EFFECTS: removes specified entry from budgetEntries
    public void removeEntry(String key) {
        budgetEntries.remove(key);
    }

    // EFFECTS: returns a list of BudgetEntry in deceding order
    // of actualAmounts
    public List<BudgetEntry> rankSpending() {
        return null;
    }

}