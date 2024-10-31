package model.budgetentries;

import org.junit.jupiter.api.BeforeEach;

public class ExpenseTest extends BudgetEntryTest {
    @Override
    @BeforeEach
    void setup() {
        budgetEntry1 = new Expense("1000", "Test", 100);
        budgetEntry2 = new Expense("1000", "Test", 100, 200);
    }
}
