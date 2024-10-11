package model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackerTest {

    Tracker tracker;

    @BeforeEach
    void setup() {
        tracker = new Tracker();
    }

    @Test
    void testConstructor() {

        assertEquals(0, tracker.getEntries().size());
    }
}
