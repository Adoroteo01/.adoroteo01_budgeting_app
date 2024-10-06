package model.budgetentries;

// A budget entry that has a name, budgeted amount, and actual amount
public class BudgetEntry {
    String name;
    double budgetAmount;
    double actualAmount;

    // EFFECTS: creates a new BudgetEntry object with given name and budgetAmount
    // the actualAmount starts at 0
    public BudgetEntry(String name, double budgetAmount) {

    }

    // getters
    public String getName() {
        return name;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    // MODIFIES: this
    // EFECTS: adds amount to actualAmount
    public void addActual(double amount) {

    }

    // MODIFIES: this
    // EFECTS: subtracts amount from actualAmount
    public void substractActual(double amount) {

    }

}
