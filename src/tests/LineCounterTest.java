package tests;

import org.junit.Test;
import owl.me.LineCounter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class LineCounterTest {
    private static final Path file = Paths.get("src" + File.separator + "tests" + File.separator + "LineCounterTest.java");

   @Test
    public void testCountNonEmptyLines() {
        assertEquals(14, LineCounter.countNonEmptyLines(file));
    }
}