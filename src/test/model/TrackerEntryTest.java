package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;

public class TrackerEntryTest {

    TrackerEntry t1;

    @BeforeEach
    void setup() {
        t1 = new TrackerEntry("Jan 1, 2021", null, 23.25);
    }

    @Test
    void testConstructor() {
        assertEquals("Jan 1, 2021", t1.getDate());
        assertEquals(null, t1.getBudgetEntry());
        assertEquals(23.25, t1.getAmount());
    }

    @Test
    public void testEquals() {
        BudgetEntry budgetEntry1 = new Expense("id1", "Groceries", 500.0);
        BudgetEntry budgetEntry2 = new Expense("id1", "Groceries", 500.0);

        TrackerEntry entry1 = new TrackerEntry("2024-11-25", budgetEntry1, 100.0);
        TrackerEntry entry2 = new TrackerEntry("2024-11-25", budgetEntry2, 100.0);

        assertEquals(entry1, entry1);

        assertEquals(entry1, entry2);
        assertEquals(entry2, entry1);

        assertNotEquals(entry1, null);
    }

    @Test
    public void testHashCode() {
        BudgetEntry budgetEntry = new Expense("id1", "Groceries", 500.0);
        TrackerEntry entry1 = new TrackerEntry("2024-11-25", budgetEntry, 100.0);
        TrackerEntry entry2 = new TrackerEntry("2024-11-25", budgetEntry, 100.0);

        assertEquals(entry1.hashCode(), entry2.hashCode());
    }
}
