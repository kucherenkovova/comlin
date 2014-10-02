package tests;

import org.junit.Test;
import owl.me.LineCounter;

import java.io.File;

import static org.junit.Assert.*;

public class LineCounterTest {
    private static final File file = new File("LineCounterTest.java");

    @Test
    public void testCountNonEmptyLines() {
        assertEquals(53, LineCounter.countNonEmptyLines(file));
    }
}