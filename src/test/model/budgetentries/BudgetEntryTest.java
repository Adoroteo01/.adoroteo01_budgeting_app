package model.budgetentries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BudgetEntryTest {

    BudgetEntry budgetEntry;

    @BeforeEach
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
    void testSetters(){
        budgetEntry.setActualAmount(2.99);
        budgetEntry.setBudgetAmount(3);
        budgetEntry.setName("changed");

        assertEquals(2.99, budgetEntry.getActualAmount());
        assertEquals(3, budgetEntry.getBudgetAmount());
        assertEquals("changed", budgetEntry.getName());
    }

    @Test
    void testAddActual() {
        budgetEntry.addActual(1);
        assertEquals(1, budgetEntry.getActualAmount());
        budgetEntry.addActual(1);
        assertEquals(2, budgetEntry.getActualAmount());
        budgetEntry.addActual(5.52);
        assertEquals(7.52, budgetEntry.getActualAmount());
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
