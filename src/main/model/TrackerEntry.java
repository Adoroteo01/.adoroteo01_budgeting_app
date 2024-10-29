package model;

import model.budgetentries.BudgetEntry;

// An entry for Tracker. Has a date, corrisponding budgetEntry, and amount
public class TrackerEntry {
    private String date;
    private BudgetEntry budgetEntry;
    private double amount;

    // EFFECT: creates new TrackerEntry with given date, budget, and amount
    public TrackerEntry(String date, BudgetEntry budgetEntry, double amount) {
        this.date = date;
        this.budgetEntry = budgetEntry;
        this.amount = amount;

    }

    public String getDate() {
        return date;
    }

    public BudgetEntry getBudgetEntry() {
        return budgetEntry;
    }

    public double getAmount() {
        return amount;
    }

    public String getBudgetEntryId() {
        return budgetEntry.getId();
    }

}
