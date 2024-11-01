package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;

public class BudgeterTest {
    Budgeter budgeter1;
    Budgeter budgeter2;
    BudgetEntry b1;
    BudgetEntry b2;
    BudgetEntry b3;
    List<BudgetEntry> listOfB;

    @BeforeEach
    void setup() {
        budgeter1 = new Budgeter();
        b1 = new Expense("1000", "B1", 800);
        b2 = new Expense("1001", "B2", 200);
        b3 = new Expense("1002", "B3", 500);

        listOfB = new ArrayList<BudgetEntry>();
        listOfB.add(b1);
        listOfB.add(b2);

        budgeter2 = new Budgeter(listOfB);

    }

    @Test
    void testConstructor() {
        assertEquals(0, budgeter1.getbudgetEntries().size());
    }

    @Test
    void testConstructor2() {
        assertEquals(2, budgeter2.getbudgetEntries().size());
        assertTrue(budgeter2.getbudgetEntries().contains(b1));
        assertTrue(budgeter2.getbudgetEntries().contains(b2));
    }

    @Test
    void testGetBudgetEntries() {
        budgeter1.addEntry(b1);
        ArrayList<BudgetEntry> output;
        output = new ArrayList<>();
        output.add(b1);
        assertTrue(output.equals(budgeter1.getbudgetEntries()));
    }

    @Test
    void testClear() {
        budgeter1.addEntry(b1);
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter1.getbudgetEntries().size());

        budgeter1.clear();
        assertEquals(0, budgeter1.getbudgetEntries().size());
    }

    @Test
    void testClearMultiple() {
        budgeter1.addEntry(b1);
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter1.getbudgetEntries().size());

        budgeter1.addEntry(b3);
        assertTrue(budgeter1.getbudgetEntries().contains(b3));
        assertEquals(2, budgeter1.getbudgetEntries().size());

        budgeter1.clear();
        assertEquals(0, budgeter1.getbudgetEntries().size());
    }

    @Test
    void testAddEntry() {
        budgeter1.addEntry(b1);
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter1.getbudgetEntries().size());

        budgeter1.addEntry(b2);
        assertTrue(budgeter1.getbudgetEntries().contains(b2));
        assertEquals(2, budgeter1.getbudgetEntries().size());
    }

    @Test
    void testRemoveEntry() {
        // removing 1 entry
        budgeter1.addEntry(b1);
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertEquals(1, budgeter1.getbudgetEntries().size());

        budgeter1.removeEntry(b1.getName());
        assertFalse(budgeter1.getbudgetEntries().contains(b1));
        assertEquals(0, budgeter1.getbudgetEntries().size());

        // removing multiple entries
        budgeter1.addEntry(b1);

        budgeter1.addEntry(b2);
        assertTrue(budgeter1.getbudgetEntries().contains(b2));
        assertEquals(2, budgeter1.getbudgetEntries().size());

        budgeter1.removeEntry(b1.getName());
        budgeter1.removeEntry(b2.getName());
        assertFalse(budgeter1.getbudgetEntries().contains(b1));
        assertFalse(budgeter1.getbudgetEntries().contains(b2));
        assertEquals(0, budgeter1.getbudgetEntries().size());

        // removing second entry
        budgeter1.addEntry(b1);
        budgeter1.addEntry(b2);

        budgeter1.removeEntry(b2.getName());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertFalse(budgeter1.getbudgetEntries().contains(b2));
        assertEquals(1, budgeter1.getbudgetEntries().size());
    }

    @Test
    void testRankSpending() {
        b1.setActualAmount(300.25);
        b2.setActualAmount(20);
        b3.setActualAmount(125.75);

        budgeter1.addEntry(b1);
        budgeter1.addEntry(b2);
        budgeter1.addEntry(b3);

        assertEquals(3, budgeter1.getbudgetEntries().size());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertTrue(budgeter1.getbudgetEntries().contains(b2));
        assertTrue(budgeter1.getbudgetEntries().contains(b3));

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();
        output.add(b1);
        output.add(b3);
        output.add(b2);

        assertTrue(output.equals(budgeter1.rankSpending()));
    }

    @Test
    void testRankSpendingSameNumber() {
        b1.setActualAmount(0);
        b2.setActualAmount(0);

        budgeter1.addEntry(b1);
        budgeter1.addEntry(b2);

        assertEquals(2, budgeter1.getbudgetEntries().size());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertTrue(budgeter1.getbudgetEntries().contains(b2));

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();
        output.add(b2);
        output.add(b1);

        System.out.println(budgeter1.rankSpending());
        assertTrue(budgeter1.rankSpending().equals(output));
    }

    @Test
    void testRankSpendingSmallDiffernce() {
        b1.setActualAmount(8.25);
        b2.setActualAmount(8.26);

        budgeter1.addEntry(b1);
        budgeter1.addEntry(b2);

        assertEquals(2, budgeter1.getbudgetEntries().size());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertTrue(budgeter1.getbudgetEntries().contains(b2));

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();
        output.add(b2);
        output.add(b1);

        assertEquals(output, budgeter1.rankSpending());
    }

    @Test
    void testRankSpendingNoEntries() {

        assertEquals(0, budgeter1.getbudgetEntries().size());

        ArrayList<BudgetEntry> output = new ArrayList<BudgetEntry>();

        assertEquals(output, budgeter1.rankSpending());
    }

    @Test
    void testFindEntry1Entry() {
        budgeter1.addEntry(b1);

        assertEquals(b1, budgeter1.findEntry("B1"));
        assertEquals(1, budgeter1.getbudgetEntries().size());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
    }

    @Test
    void testFindEntry2Entries() {
        budgeter1.addEntry(b1);
        budgeter1.addEntry(b2);

        assertEquals(b2, budgeter1.findEntry("B2"));
        assertEquals(2, budgeter1.getbudgetEntries().size());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
        assertTrue(budgeter1.getbudgetEntries().contains(b2));
    }

    @Test
    void testFindEntryNotFound() {
        budgeter1.addEntry(b1);

        assertEquals(null, budgeter1.findEntry("B7"));
        assertEquals(1, budgeter1.getbudgetEntries().size());
        assertTrue(budgeter1.getbudgetEntries().contains(b1));
    }
}
