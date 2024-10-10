package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.budgetentries.BudgetEntry;

public class BudgeterTest {
    Budgeter budgeter;
    BudgetEntry b1;
    BudgetEntry b2;
    BudgetEntry b3;

    @BeforeEach
    void setup() {
        budgeter = new Budgeter();
        b1 = new BudgetEntry("B1", 0);
        b2 = new BudgetEntry("B2", 200);
        b3 = new BudgetEntry("B3", 60);

    }

    @Test
    void testConstructor() {
        assertEquals(0, budgeter.getbudgetEntries().size());
    }

    @Test
    // TODO: do I even need to test this?
    void testGetBudgetEntries() {
        budgeter.addEntry(b1);
        ArrayList<BudgetEntry> output;
        output = new ArrayList<>();
        output.add(b1);
        assertTrue(output.equals(budgeter.getbudgetEntries()));
    }


    @Test
    void testClear() {
        budgeter.addEntry(b1);
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter.getbudgetEntries().size());

        budgeter.clear();
        assertEquals(0, budgeter.getbudgetEntries().size());
    }

    @Test
    void testClearMultiple() {
        budgeter.addEntry(b1);
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter.getbudgetEntries().size());

        budgeter.addEntry(b3);
        assertTrue(budgeter.getbudgetEntries().contains(b3));
        assertEquals(2, budgeter.getbudgetEntries().size());


        budgeter.clear();
        assertEquals(0, budgeter.getbudgetEntries().size());
    }

    @Test
    void testAddEntry() {
        budgeter.addEntry(b1);
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter.getbudgetEntries().size());

        budgeter.addEntry(b2);
        assertTrue(budgeter.getbudgetEntries().contains(b2));
        assertEquals(2, budgeter.getbudgetEntries().size());
    }

    @Test
    void testRemoveEntry() {
        // removing 1 entry
        budgeter.addEntry(b1);
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter.getbudgetEntries().size());

        budgeter.removeEntry(b1.getName());
        assertFalse(budgeter.getbudgetEntries().contains(b1));
        assertEquals(0, budgeter.getbudgetEntries().size());

        // removing multiple entries
        budgeter.addEntry(b1);

        budgeter.addEntry(b2);
        assertTrue(budgeter.getbudgetEntries().contains(b2));
        assertEquals(2, budgeter.getbudgetEntries().size());

        budgeter.removeEntry(b1.getName());
        budgeter.removeEntry(b2.getName());
        assertFalse(budgeter.getbudgetEntries().contains(b1));
        assertFalse(budgeter.getbudgetEntries().contains(b2));
        assertEquals(0, budgeter.getbudgetEntries().size());

        // removing second entry
        budgeter.addEntry(b1);
        budgeter.addEntry(b2);

        budgeter.removeEntry(b2.getName());
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertFalse(budgeter.getbudgetEntries().contains(b2));
        assertEquals(1, budgeter.getbudgetEntries().size());
    }

    @Test
    void testRankSpending() {
        b1.setActualAmount(300.25);
        b2.setActualAmount(20);
        b3.setActualAmount(125.75);

        budgeter.addEntry(b1);
        budgeter.addEntry(b2);
        budgeter.addEntry(b3);

        assertEquals(3, budgeter.getbudgetEntries().size());
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertTrue(budgeter.getbudgetEntries().contains(b2));
        assertTrue(budgeter.getbudgetEntries().contains(b3));

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();
        output.add(b1);
        output.add(b3);
        output.add(b2);

        assertTrue(output.equals(budgeter.rankSpending()));
    }

    @Test
    void testRankSpendingSameNumber() {
        b1.setActualAmount(0);
        b2.setActualAmount(0);

        budgeter.addEntry(b1);
        budgeter.addEntry(b2);

        assertEquals(2, budgeter.getbudgetEntries().size());
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertTrue(budgeter.getbudgetEntries().contains(b2));

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();
        output.add(b2);
        output.add(b1);

        System.out.println(budgeter.rankSpending());
        assertTrue(budgeter.rankSpending().equals(output));
    }

    @Test
    void testRankSpendingSmallDiffernce() {
        b1.setActualAmount(8.25);
        b2.setActualAmount(8.26);

        budgeter.addEntry(b1);
        budgeter.addEntry(b2);

        assertEquals(2, budgeter.getbudgetEntries().size());
        assertTrue(budgeter.getbudgetEntries().contains(b1));
        assertTrue(budgeter.getbudgetEntries().contains(b2));

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();
        output.add(b2);
        output.add(b1);

        assertEquals(output, budgeter.rankSpending());
    }

    @Test
    void testRankSpendingNoEntries() {

        assertEquals(0, budgeter.getbudgetEntries().size());

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();

        assertEquals(output, budgeter.rankSpending());
    }
}
