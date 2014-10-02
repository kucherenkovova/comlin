package owl.me;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LineCounter {
   public static long countNonEmptyLines(Path path) {
        long counter = 0;
        String tmp;
        try(BufferedReader in = Files.newBufferedReader(path)) {
            while((tmp = in.readLine()) != null) {
                if(!tmp.trim().isEmpty())
                    counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    private LineCounter() {}
}