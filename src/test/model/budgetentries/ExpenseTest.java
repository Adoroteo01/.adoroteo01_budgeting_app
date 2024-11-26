package model.budgetentries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpenseTest extends BudgetEntryTest {
    @Override
    @BeforeEach
    void setup() {
        budgetEntry1 = new Expense("1000", "Test", 100);
        budgetEntry2 = new Expense("1000", "Test", 100, 200);
    }

    @Test
    public void testEquals() {
        BudgetEntry entry1 = new Expense("id1", "Groceries", 500.0);
        BudgetEntry entry2 = new Expense("id1", "Groceries", 500.0);
        BudgetEntry entry3 = new Expense("id2", "Rent", 1000.0);

        assertEquals(entry1, entry1);

        assertEquals(entry1, entry2);
        assertEquals(entry2, entry1);

        BudgetEntry entry4 = new Expense("id1", "Groceries", 500.0);
        assertEquals(entry1, entry2);
        assertEquals(entry2, entry4);
        assertEquals(entry1, entry4);

        assertNotEquals(entry1, null);

        assertNotEquals(entry1, entry3);
    }

    @Test
    public void testHashCode() {
        BudgetEntry entry1 = new Expense("id1", "Groceries", 500.0);
        BudgetEntry entry2 = new Expense("id1", "Groceries", 500.0);

        assertEquals(entry1.hashCode(), entry2.hashCode());
    }
}
