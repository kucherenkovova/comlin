package tests;

import org.junit.Test;
import owl.me.ArgumentParser;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ArgumentParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void NoArgumentsTest() {
        String[] arguments = {};
        new ArgumentParser(arguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void NotEnoughArgumentsTest() {
        String[] arguments = {"/home/asus/IdeaProjects"};
        new ArgumentParser(arguments);
    }

    @Test(expected = IllegalArgumentException.class)
    public void FirstArgumentIsDirectoryTest() {
        String[] arguments = {"qwerty", "java", "cpp", "c"};
        new ArgumentParser(arguments);
    }

    @Test
    public void getDirectoryTest() {
        String[] arguments = {"/home/asus/IdeaProjects", "java", "cpp", "c"};
        File expected = new File(arguments[0]);
        ArgumentParser parser = new ArgumentParser(arguments);
        assertEquals("Directory was not /home/asus/IdeaProjects", expected, parser.getDirectory());
    }

    @Test
    public void getExtensionsTest() {
        String[] arguments = {"/home/asus/IdeaProjects", "java", "cpp", "c"};
        String[] expected = {"java", "cpp", "c"};
        ArgumentParser parser = new ArgumentParser(arguments);
        String[] result = parser.getExtensions();
        for(int i = 0 ; i < result.length ; i++) {
            assertEquals(expected[i], result[i]);
        }
    }
}