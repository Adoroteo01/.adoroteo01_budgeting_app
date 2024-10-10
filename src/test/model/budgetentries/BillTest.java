package model.budgetentries;

import org.junit.jupiter.api.BeforeEach;

public class BillTest extends BudgetEntryTest {

    @Override
    @BeforeEach
    void setup() {
        budgetEntry = new Bill("Test", 100);
    }

}
