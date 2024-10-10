package model.budgetentries;

// A budget entry of a bill that has a name, budgeted amount, and actual amount
public class Bill extends BudgetEntry {

    public Bill(String name, double budgetAmount) {
        super(name, budgetAmount);
    }
}
