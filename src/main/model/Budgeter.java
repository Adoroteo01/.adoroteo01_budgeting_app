package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import model.budgetentries.BudgetEntry;

// A budget tracker that has the name of the budget, the start and end date, 
// and a map of budget entries
public class Budgeter {

    private String name;
    private String startDate;
    private String endDate;

    private HashMap<String, BudgetEntry> budgetEntries;

    // REQUIRES: startDate and endDate mus be in the form "yyyy-mm-dd"
    // EFFECTS: creates a new budgeter with given name, start and end date,
    // and no budget entries
    public Budgeter(String name, String startDate, String endDate) {

        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        budgetEntries = new HashMap<String, BudgetEntry>();
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
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
    // TODO: write tests for this
    private void sort(List<BudgetEntry> entries) {
        Comparator<BudgetEntry> byActualAmount;
        byActualAmount = Comparator.comparingDouble(BudgetEntry::getActualAmount).reversed();

        entries.sort(byActualAmount);
    }

}