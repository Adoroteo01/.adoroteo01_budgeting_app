package model.budgetentries;

// A budget entry that has a name, budgeted amount, and actual amount
public abstract class BudgetEntry {
    protected String name;
    protected double budgetAmount;
    protected double actualAmount;
    private static int idCounter = 1000;
    protected int id;

    // REQUIRES: id is unique to each BudgetEntry Object
    // EFFECTS: creates a new BudgetEntry object with given name, budgetAmount, and a unique id.
    // the actualAmount starts at 0
    // TODO: Update constuctor for id
    public BudgetEntry(String name, double budgetAmount) {
        this.name = name;
        this.budgetAmount = budgetAmount;
        this.actualAmount = 0;
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

    // TODO: Implement
    public int getId() {
        return 0; // stub
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
        actualAmount += amount;
    }

    // MODIFIES: this
    // EFECTS: subtracts amount from actualAmount
    public void substractActual(double amount) {
        actualAmount -= amount;
    }

}
