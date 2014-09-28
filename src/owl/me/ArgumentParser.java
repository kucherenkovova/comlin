package owl.me;

import java.io.File;
import java.util.Arrays;

public class ArgumentParser {
    private File directory;
    private String[] extensions;

    public ArgumentParser(String args[]) {
        if (args.length < 2)
            throw new IllegalArgumentException("Too short argument list");
        else {
            directory = new File(args[0]);
            if(!directory.isDirectory()) {
                throw new IllegalArgumentException(directory.getName() + " is not a directory");
            }
            extensions = Arrays.copyOfRange(args, 1, args.length);
        }
    }

    public File getDirectory() {
        return directory;
    }

    public String[] getExtensions() {
        return extensions;
    }
}
