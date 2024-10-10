package model.budgetentries;

import org.junit.jupiter.api.BeforeEach;

public class IncomeTest extends BudgetEntryTest {
    @Override
    @BeforeEach
    void setup() {
        budgetEntry = new Income("Test", 100);
    }
}
