package owl.me;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long total = 0;
        ArgumentParser parser = new ArgumentParser(args);
        File directory = parser.getDirectory();
        FileFinder finder = new FileFinder(parser.getExtensions());
        List<File> files = finder.findFiles(directory);
        for(File file : files) {
            total += LineCounter.countNonEmptyLines(file);
        }
        System.out.println("There are " + total + " strings of code in your project!");
    }
}
