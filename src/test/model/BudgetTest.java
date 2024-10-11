package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.budgetentries.*;

public class BudgetTest {

    Budget budget;
    Bill b1;

    TrackerEntry t1;

    @BeforeEach
    void setup() {
        budget = new Budget("Test Budget", "Jan 1, 2024", "Feb 1, 2024");

        b1 = new Bill("B1", 300);

        // t1 = new TrackerEntry("df", "f", 2); // TODO: finish TrackerEntry implementation
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
    void testAddTrackerEntry() {
        // TODO: Finish this after TrackerEntry implementation

    }

}
