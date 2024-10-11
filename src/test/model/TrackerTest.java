package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackerTest {

    Tracker tracker;

    TrackerEntry t1;

    @BeforeEach
    void setup() {
        tracker = new Tracker();
        t1 = new TrackerEntry("Jan 1 2024", null, 9.99);
    }

    @Test
    void testConstructor() {

        assertEquals(0, tracker.getEntries().size());
    }

    @Test
    void testAddEntry() {
        tracker.addEntry(t1);
        assertEquals(1, tracker.getEntries().size());
        assertEquals(t1, tracker.getEntries().get(0));
    }
}
