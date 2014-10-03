package tests;

import org.junit.Before;
import org.junit.Test;
import owl.me.FileFinder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

public class FileFinderTest {
    private static final Path tempHome = Paths.get("src" + File.separator + "tests" + File.separator);
    private static final String[] extensions = {"java", "c", "cpp"};
    private static final String[] filenames = {"javaFile1", "binary.c", "cppFile1",
                                               "java", "cFile1", "textFile",
                                               "javaFile2", "cFile2"};
    private static final List<String> expected = Arrays.asList("javaFile1.java", "cppFile1.cpp", "cFile1.c",
                                                               "javaFile2.java", "cFile2.c");
    @Before
    public void setUp() throws IOException {
        Path tempDirectory = Files.createTempDirectory(tempHome, "temp");
        Files.createTempFile(tempDirectory, filenames[0], ".java");
        Files.createTempFile(tempDirectory, filenames[1], ".bin");
        Files.createTempFile(tempDirectory, filenames[2], ".cpp");
        Path dir1 = Files.createTempDirectory(tempDirectory, "dir1.java");
        Path dir2 = Files.createTempDirectory(dir1, "dir2");
        Files.createTempFile(dir1, filenames[3], "");
        Files.createTempFile(dir1, filenames[4], ".c");
        Files.createTempFile(dir1, filenames[5], ".txt");
        Files.createTempFile(dir2, filenames[6], ".java");
        Files.createTempFile(dir2, filenames[3], ".c");
    }

    @Test
    public void findFilesTest() {
        FileFinder finder = new FileFinder(extensions);
        List<Path> foundFiles = finder.findFiles(Paths.get(tempHome + "temp" + File.separator));
        for(Path f : foundFiles) {
            if(!expected.contains(f.getFileName()))
                fail();
            else
                expected.remove(f.getFileName());
        }
        if(!expected.isEmpty())
            fail();
    }
    /*
    private static final String testDirectory = new File("").getAbsolutePath() + File.separator + "temp" + File.separator;
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
        new File(testDirectory + File.separator + "java" + File.separator).mkdirs();
        new File(testDirectory + File.separator + "java" + File.separator + filenames[2]).createNewFile();
        new File(testDirectory + File.separator + "java" + File.separator + filenames[3]).createNewFile();
        new File(testDirectory + File.separator + "c" + File.separator).mkdirs();
        new File(testDirectory + File.separator + "c" + File.separator + filenames[4]).createNewFile();
        new File(testDirectory + File.separator + "c" + File.separator + filenames[5]).createNewFile();
        new File(testDirectory + File.separator + "c" + File.separator +"java" + File.separator).mkdirs();
        new File(testDirectory + File.separator + "c" + File.separator +"java" + File.separator +
                filenames[6]).createNewFile();
        new File(testDirectory + File.separator + "c" + File.separator + "java" + File.separator
                + filenames[7]).createNewFile();
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
        new File(testDirectory).delete();
    }
    */
}