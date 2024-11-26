package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testEquals() {
        Tracker tracker1 = new Tracker();
        Tracker tracker2 = new Tracker();

        List<TrackerEntry> entries = new ArrayList<>();
        entries.add(new TrackerEntry("2024-11-25", null, 100.0));

        tracker1.getEntries().addAll(entries);
        tracker2.getEntries().addAll(entries);

        assertEquals(tracker1, tracker1);

        assertEquals(tracker1, tracker2);
        assertEquals(tracker2, tracker1);

        assertEquals(tracker1, tracker2);

        assertNotEquals(tracker1, null);
    }

    @Test
    public void testHashCode() {
        Tracker tracker1 = new Tracker();
        Tracker tracker2 = new Tracker();

        List<TrackerEntry> entries = new ArrayList<>();
        entries.add(new TrackerEntry("2024-11-25", null, 100.0));

        tracker1.getEntries().addAll(entries);
        tracker2.getEntries().addAll(entries);

        assertEquals(tracker1.hashCode(), tracker2.hashCode());
    }
}
