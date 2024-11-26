package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.budgetentries.*;
import model.exceptions.InvalidBudgetEntryException;

public class BudgetTest {

    Budget budget1;
    Budget budget2;

    Tracker tracker;
    Budgeter budgeter;

    TrackerEntry t1;
    BudgetEntry b1;

    @BeforeEach
    void setup() {

        tracker = new Tracker();
        budgeter = new Budgeter();

        budget1 = new Budget("Test Budget", "Jan 1, 2024", "Feb 1, 2024");
        budget2 = new Budget("Test Budget", "Jan 1, 2024", "Feb 1, 2024", budgeter, tracker);

        b1 = new Expense("1000", "B1", 300);
        budget1.addBudgetEntry(b1);
    }

    @Test
    void testConstructor() {
        assertEquals("Test Budget", budget1.getName());
        assertEquals("Jan 1, 2024", budget1.getStartDate());
        assertEquals("Feb 1, 2024", budget1.getEndDate());
    }

    @Test
    void testConstructorWithBudgeterTracker() {
        assertEquals("Test Budget", budget2.getName());
        assertEquals("Jan 1, 2024", budget2.getStartDate());
        assertEquals("Feb 1, 2024", budget2.getEndDate());
        assertEquals(tracker, budget2.getTracker());
        assertEquals(budgeter, budget2.getBudgeter());
    }

    @Test
    void testAddBudgetEntry() {
        budget1.addBudgetEntry(b1);

        assertEquals(b1, budget1.getBudgetEntries().get(0));

    }

    @Test
    void testAddTrackerEntryExistingBudgetEntry() throws InvalidBudgetEntryException {
        budget1.addTrackerEntry("Jan 1, 2024", "B1", 3.99);

        assertEquals(3.99, budget1.getBudgeter().findEntry("B1").getActualAmount());   
        assertEquals(3.99, budget1.getTracker().getEntries().get(0).getAmount());     
        assertEquals(3.99, budget1.getTracker().getEntries().get(0).getAmount());     
        assertEquals("Jan 1, 2024", budget1.getTracker().getEntries().get(0).getDate());     
        assertEquals(b1, budget1.getTracker().getEntries().get(0).getBudgetEntry());     
    }

    @Test
    void testAddTrackerEntryBudgetEntryNotFound() {

        assertThrows(InvalidBudgetEntryException.class, () -> budget1.addTrackerEntry("Jan 1, 2024", "B120", 8.95));
    }


    

}
