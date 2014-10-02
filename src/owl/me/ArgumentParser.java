package owl.me;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ArgumentParser {
    private Path directory;
    private String[] extensions;

    public ArgumentParser(String args[]) {
        if (args.length < 2)
            throw new IllegalArgumentException("Too short argument list");
        else {
            directory = Paths.get(args[0]);
            if(!Files.isDirectory(directory)) {
                throw new IllegalArgumentException(directory + " is not a directory");
            }
            extensions = Arrays.copyOfRange(args, 1, args.length);
        }
    }

    public Path getDirectory() {
        return directory;
    }

    public String[] getExtensions() {
        return extensions;
    }
}
