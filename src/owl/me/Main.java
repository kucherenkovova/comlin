package owl.me;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser(args);
        File directory = parser.getDirectory();
    }
}
