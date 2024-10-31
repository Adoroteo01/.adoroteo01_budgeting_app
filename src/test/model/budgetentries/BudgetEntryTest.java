package model.budgetentries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class BudgetEntryTest {

    protected BudgetEntry budgetEntry1;
    protected BudgetEntry budgetEntry2;

    abstract void setup();

    @Test
    void testConstructor() {
        assertEquals("1000", budgetEntry1.getId());
        assertEquals("Test", budgetEntry1.getName());
        assertEquals(100, budgetEntry1.getBudgetAmount());
        assertEquals(0, budgetEntry1.getActualAmount());
    }

    @Test
    void testConstructorWithActualAmount() {
        assertEquals("1000", budgetEntry2.getId());
        assertEquals("Test", budgetEntry2.getName());
        assertEquals(100, budgetEntry2.getBudgetAmount());
        assertEquals(200, budgetEntry2.getActualAmount());
    }

    @Test
    void testSetters() {
        budgetEntry1.setActualAmount(2.99);
        budgetEntry1.setBudgetAmount(3);
        budgetEntry1.setName("changed");

        assertEquals(2.99, budgetEntry1.getActualAmount());
        assertEquals(3, budgetEntry1.getBudgetAmount());
        assertEquals("changed", budgetEntry1.getName());
    }

    @Test
    void testAddActual() {
        budgetEntry1.addActual(1);
        assertEquals(1, budgetEntry1.getActualAmount());
        budgetEntry1.addActual(1);
        assertEquals(2, budgetEntry1.getActualAmount());
        budgetEntry1.addActual(5.52);
        assertEquals(7.52, budgetEntry1.getActualAmount());
    }

    @Test
    void testSubtractActual() {
        budgetEntry1.substractActual(1);
        assertEquals(-1, budgetEntry1.getActualAmount());
        budgetEntry1.substractActual(1);
        assertEquals(-2, budgetEntry1.getActualAmount());
        budgetEntry1.substractActual(5.52);
        assertEquals(-7.52, budgetEntry1.getActualAmount());
    }
}
