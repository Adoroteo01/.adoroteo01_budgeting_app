package model.budgetentries;

import org.junit.jupiter.api.BeforeEach;

public class ExpenseTest extends BudgetEntryTest {
    @Override
    @BeforeEach
    void setup() {
        budgetEntry = new Expense("1000","Test", 100);
    }
}
