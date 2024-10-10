package model;

import javax.sound.midi.Track;

// a budget that has a name, start date, end date, budgeter and a spending tracker
public class Budget {

    private String name;
    private String startDate;
    private String endDate;

    private Budgeter budgeter;
    private Tracker tracker;

    // creates a new budget with a new budgeter and tracker
    public Budget() {

    }

    // MODIFIES: budgeter
    // EFFECT: adds a new Bill entry to budgeter
    public void addBill() {

    }

    // MODIFIES: budgeter
    // EFFECT: adds a new Debt entry to budgeter
    public void addDebt() {

    }

    // MODIFIES: budgeter
    // EFFECT: adds a new Expense entry to budgeter
    public void addExpense() {

    }

    // MODIFIES: budgeter
    // EFFECT: adds a new Income entry to budgeter
    public void addIncome() {

    }


    // MODIFIES: tracker
    // EFFECT: adds a new TrackerEnter entry to tracker
    public void track() {

    }

}
