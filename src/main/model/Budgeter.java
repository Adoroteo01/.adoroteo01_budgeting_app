package model;

import java.util.*;

import model.budgetentries.BudgetEntry;

// A budget tracker that has a map of budget entries
public class Budgeter {

    private HashMap<String, BudgetEntry> budgetEntries;

    // EFFECTS: creates a new budgeter with no budget entries
    public Budgeter() {
        budgetEntries = new HashMap<String, BudgetEntry>();
    }

    // EFFECTS: creates a new budgeter with given budget entries
    public Budgeter(List<BudgetEntry> budgetEntries) {
        this.budgetEntries = new HashMap<String, BudgetEntry>();

        for (BudgetEntry budgetEntry : budgetEntries) {
            this.budgetEntries.put(budgetEntry.getName(), budgetEntry);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(budgetEntries);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            {
                return true;
            }
        }
        if (o == null || getClass() != o.getClass()) {
            {
                return false;
            }
        }
        Budgeter budgeter = (Budgeter) o;
        return Objects.equals(budgetEntries, budgeter.budgetEntries);
    }

    // EFFECTS: returns an ArrayList of all the entries in budgetEntries
    public ArrayList<BudgetEntry> getbudgetEntries() {
        return new ArrayList<BudgetEntry>(budgetEntries.values());
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

    // EFFECTS: if BudgetEntry with key = name is in budgetEntries, returns that
    // BudgetEntry
    // if key with that name does not exist in budgetEntries returns null
    public BudgetEntry findEntry(String name) {

        return budgetEntries.get(name);
    }

    // MODIFIES: this
    // EFFECTS: removes specified entry from budgetEntries
    public void removeEntry(String key) {
        budgetEntries.remove(key);
    }

    // EFFECTS: returns a list of BudgetEntry objects in budgetEntries map.
    // list is in descending order of actualAmounts
    public List<BudgetEntry> rankSpending() {
        List<BudgetEntry> entries;
        entries = new ArrayList<BudgetEntry>(getbudgetEntries());
        sort(entries);

        return entries;
    }

    // MODIFIES: entries
    // EFFECTS: sorts entries in decending order of BudgetEntry.getActualAmount()
    private void sort(List<BudgetEntry> entries) {
        Comparator<BudgetEntry> byActualAmount;
        byActualAmount = Comparator.comparingDouble(BudgetEntry::getActualAmount).reversed();

        entries.sort(byActualAmount);

        EventLog.getInstance()
                .logEvent(new Event(
                        "Created Summary for Budget Entries"));
    }

}