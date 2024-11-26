package model;

import model.budgetentries.BudgetEntry;

import java.util.Objects;

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

        EventLog.getInstance()
                .logEvent(new Event(
                        "Created new Tracker Entry for $" + amount + " for Budget Entry:" + budgetEntry.getName()));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrackerEntry that = (TrackerEntry) o;
        return Double.compare(getAmount(), that.getAmount()) == 0 && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getBudgetEntry(), that.getBudgetEntry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getBudgetEntry(), getAmount());
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
