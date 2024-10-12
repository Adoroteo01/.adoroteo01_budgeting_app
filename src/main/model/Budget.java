package model;

import java.util.List;

import model.Exceptions.InvalidBudgetEntryException;
import model.budgetentries.BudgetEntry;

// a budget that has a name, start date, end date, budgeter and a spending tracker
public class Budget {

    private String name;
    private String startDate;
    private String endDate;

    private Budgeter budgeter;
    private Tracker tracker;

    // EFFECT: creates a new budget with given name, start date, end date,
    // and new budgeter and tracker.
    public Budget(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        budgeter = new Budgeter();
        tracker = new Tracker();
    }

    // EFFECT: returns name
    public String getName() {
        return name;
    }

    // EFFECT: returns startDate
    public String getStartDate() {
        return startDate;
    }

    // EFFECT: returns endDate
    public String getEndDate() {
        return endDate;
    }

    public Budgeter getBudgeter() {
        return budgeter;
    }

    public Tracker getTracker() {
        return tracker;
    }

    // MODIFIES: budgeter
    // EFFECT: adds a BudgetEntry to budgeter
    public void addBudgetEntry(BudgetEntry budgetEntry) {
        budgeter.addEntry(budgetEntry);
    }

    // EFFECT: returns budgeter's budget entries
    public List<BudgetEntry> getBudgetEntries() {
        return budgeter.getbudgetEntries();
    }

    // MODIFIES: tracker
    // EFFECT: if budgeter.findEntry(budgetEntryName) is null:
    // throws InvalidBudgetEntryException
    //
    // if not null:
    // adds amount to specified budget entry's actualAmount
    // adds a new TrackerEnter to tracker
    public void addTrackerEntry(String date, String budgetEntryName, double amount) throws InvalidBudgetEntryException {
        if (budgeter.findEntry(budgetEntryName) == null) {
            throw new InvalidBudgetEntryException();
        }

        BudgetEntry entry = budgeter.findEntry(budgetEntryName);
        entry.addActual(amount);

        tracker.addEntry(new TrackerEntry(date, entry, amount));
    }

}
