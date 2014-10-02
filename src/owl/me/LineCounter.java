package owl.me;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {
   public static long countNonEmptyLines(File path) {
        long counter = 0;
        String tmp;
        try(BufferedReader in = new BufferedReader(new FileReader(path))) {
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