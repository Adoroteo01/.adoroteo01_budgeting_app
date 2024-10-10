package model;

import javax.sound.midi.Track;

// a budget that has a name, start date, end date, budgeter and a spending tracker
public class Budget {

    private String name;
    private String startDate;
    private String endDate;

    private Budgeter budgeter;
    private Tracker tracker;

    // TODO
    // EFFECT: creates a new budget with given name, start date, end date,
    //         and new budgeter and tracker.
    public Budget() {

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

    // TODO
    // MODIFIES: budgeter
    // EFFECT: adds a new Bill entry to budgeter
    public void addBill() {

    }

    // TODO
    // MODIFIES: budgeter
    // EFFECT: adds a new Debt entry to budgeter
    public void addDebt() {

    }

    // TODO
    // MODIFIES: budgeter
    // EFFECT: adds a new Expense entry to budgeter
    public void addExpense() {

    }

    // TODO
    // MODIFIES: budgeter
    // EFFECT: adds a new Income entry to budgeter
    public void addIncome() {

    }

    // TODO
    // MODIFIES: tracker
    // EFFECT: if budgeter.find(budgetEntryName) is null:
    //         throws InvalidBudgetEntry Exception
    //
    //         if not null:
    //         adds amount to specified budget entry's actualAmount
    //         adds a new TrackerEnter to tracker
    public void addTrackerEntry(String date, String budgetEntryName, int amount) {

    }

}
