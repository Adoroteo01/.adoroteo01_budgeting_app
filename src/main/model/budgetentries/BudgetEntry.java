package model.budgetentries;

import java.util.Objects;

// A budget entry that has a name, budgeted amount, and actual amount
public abstract class BudgetEntry {
    protected String name;
    protected double budgetAmount;
    protected double actualAmount;
    protected String id;

    // REQUIRES: id is unique to each BudgetEntry Object
    // EFFECTS: creates a new BudgetEntry object with given name, budgetAmount, and
    // a unique id.
    // the actualAmount starts at 0
    public BudgetEntry(String id, String name, double budgetAmount) {
        this.id = id;
        this.name = name;
        this.budgetAmount = budgetAmount;
        this.actualAmount = 0;
    }

    public BudgetEntry(String id, String name, double budgetAmount, double actualAmount) {
        this.id = id;
        this.name = name;
        this.budgetAmount = budgetAmount;
        this.actualAmount = actualAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            {
                return false;
            }
        }
        BudgetEntry that = (BudgetEntry) o;
        return Double.compare(getBudgetAmount(), that.getBudgetAmount()) == 0
                && Double.compare(getActualAmount(), that.getActualAmount()) == 0
                && Objects.equals(getName(), that.getName()) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBudgetAmount(), getActualAmount(), getId());
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

    public String getId() {
        return id;
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
