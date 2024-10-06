package model.budgetentries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestBudgetEntry {

    BudgetEntry budgetEntry;

    @BeforeAll
    void setup() {
        budgetEntry = new BudgetEntry("Test", 100);
    }

    @Test
    void testConstructor() {
        assertEquals("Test", budgetEntry.getName());
        assertEquals(100, budgetEntry.getBudgetAmount());
        assertEquals(0, budgetEntry.getActualAmount());
    }

    @Test
    void testAddActual() {
        budgetEntry.addActual(1);
        assertEquals(1, budgetEntry.getActualAmount());
        budgetEntry.addActual(1);
        assertEquals(2, budgetEntry.getActualAmount());
        budgetEntry.addActual(5.52);
        assertEquals(7.2, budgetEntry.getActualAmount());
    }

    @Test
    void testSubtractActual() {
        budgetEntry.substractActual(1);
        assertEquals(-1, budgetEntry.getActualAmount());
        budgetEntry.substractActual(1);
        assertEquals(-2, budgetEntry.getActualAmount());
        budgetEntry.substractActual(5.52);
        assertEquals(-7.52, budgetEntry.getActualAmount());
    }
}
