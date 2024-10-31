package model.budgetentries;

// A budget entry of an expense that has a name, budgeted amount, and actual amount
public class Expense extends BudgetEntry {

    // EFFECT: creates Expense with given id, name, and budgetAmount
    public Expense(String id, String name, double budgetAmount) {
        super(id, name, budgetAmount);
    }

    // EFFECT: creates Expense with given id, name, budgetAmount, and actualAmount
    public Expense(String id, String name, double budgetAmount, double actualAmount) {
        super(id, name, budgetAmount, actualAmount);
    }
}
