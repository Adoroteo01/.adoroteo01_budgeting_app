package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.InvalidBudgetEntryException;
import model.budgetentries.*;

public class BudgetTest {

    Budget budget;
    Bill b1;

    TrackerEntry t1;

    @BeforeEach
    void setup() {
        budget = new Budget("Test Budget", "Jan 1, 2024", "Feb 1, 2024");

        b1 = new Bill("B1", 300);
        budget.addBudgetEntry(b1);
    }

    @Test
    void testConstructor() {
        assertEquals("Test Budget", budget.getName());
        assertEquals("Jan 1, 2024", budget.getStartDate());
        assertEquals("Feb 1, 2024", budget.getEndDate());
    }

    @Test
    void testAddBudgetEntry() {
        budget.addBudgetEntry(b1);

        Budgeter budgeter;
        budgeter = budget.getBudgeter();

        assertEquals(b1, budgeter.getbudgetEntries().get(0));

    }

    @Test
    void testAddTrackerEntryExistingBudgetEntry() throws InvalidBudgetEntryException {
        budget.addTrackerEntry("Jan 1, 2024", "B1", 3.99);

        assertEquals(3.99, budget.getBudgeter().findEntry("B1").getActualAmount());   
        assertEquals(3.99, budget.getTracker().getEntries().get(0).getAmount());     
        assertEquals(3.99, budget.getTracker().getEntries().get(0).getAmount());     
        assertEquals("Jan 1, 2024", budget.getTracker().getEntries().get(0).getDate());     
        assertEquals(b1, budget.getTracker().getEntries().get(0).getBudgetEntry());     
    }

    @Test
    void testAddTrackerEntryBudgetEntryNotFound() {

        assertThrows(InvalidBudgetEntryException.class, () -> budget.addTrackerEntry("Jan 1, 2024", "B120", 8.95));
    }

}
