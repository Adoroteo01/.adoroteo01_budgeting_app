package model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
