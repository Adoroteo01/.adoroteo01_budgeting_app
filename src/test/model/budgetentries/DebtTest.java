package model.budgetentries;

import org.junit.jupiter.api.BeforeEach;

public class DebtTest extends BudgetEntryTest {
    @Override
    @BeforeEach
    void setup() {
        budgetEntry = new Debt("Test", 100);
    }
}
