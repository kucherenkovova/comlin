package owl.me;

import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long total = 0;
        ArgumentParser parser = new ArgumentParser(args);
        Path directory = parser.getDirectory();
        FileFinder finder = new FileFinder(parser.getExtensions());
        List<Path> files = finder.findFiles(directory);
        for(Path file : files) {
            total += LineCounter.countNonEmptyLines(file);
        }
        System.out.println("There are " + total + " strings of code in your project!");
    }
}
