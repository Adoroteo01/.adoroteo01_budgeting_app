package model.budgetentries;

// A budget entry that has a name, budgeted amount, and actual amount
public class BudgetEntry {
    String name;
    float budgetAmount;
    float actualAmount;

    // EFFECTS: creates a new BudgetEntry object with given name and budgetAmount
    // the actualAmount starts at 0
    public BudgetEntry(String name, float budgetAmount) {

    }

    public String getName() {
        return name;
    }

    public float getBudgetAmount() {
        return budgetAmount;
    }

    public float getActualAmount() {
        return actualAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudgetAmount(float budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public void setActualAmount(float actualAmount) {
        this.actualAmount = actualAmount;
    }

}
