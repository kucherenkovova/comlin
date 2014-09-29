package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import owl.me.FileFinder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

public class FileFinderTest {
    private static final String testDirectory = new File("").getAbsolutePath() + "/temp/";
    private static final String[] extensions = {"java", "c", "cpp"};
    private static final String[] filenames = {"javaFile1.java", "binary.c.bin", "cppFile1.cpp",
                                               "java", "cFile1.c", "textFile.txt",
                                               "javaFile2.java", "cFile2.c"};
    private static final List<String> expected = Arrays.asList("javaFile1.java", "cppFile1.cpp", "cFile1.c",
                                                               "javaFile2.java", "cFile2.c");


    @Before
    public void setUp() throws IOException {
        new File(testDirectory).mkdirs();
        new File(testDirectory + filenames[0]).createNewFile();
        new File(testDirectory + filenames[1]).createNewFile();
        new File(testDirectory + "/java/").mkdirs();
        new File(testDirectory + "/java/" + filenames[2]).createNewFile();
        new File(testDirectory + "/java/" + filenames[3]).createNewFile();
        new File(testDirectory + "/c/").mkdirs();
        new File(testDirectory + "/c/" + filenames[4]).createNewFile();
        new File(testDirectory + "/c/" + filenames[5]).createNewFile();
        new File(testDirectory + "/c/java/").mkdirs();
        new File(testDirectory + "/c/java/" + filenames[6]).createNewFile();
        new File(testDirectory + "/c/java/" + filenames[7]).createNewFile();
    }

    @Test
    public void findFilesTest() {
        FileFinder finder = new FileFinder(extensions);
        List<File> foundFiles = finder.findFiles(new File(testDirectory));
        for(File f : foundFiles) {
            if(!expected.contains(f.getName()))
                fail();
        }
    }

    @After
    public void tearDown() {
       // new File(testDirectory).delete();
    }
}