package owl.me;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileFilter;


public final class FileFinder {
    private String[] extensions;

    public FileFinder(String[] extensions) {
        this.extensions = extensions;
    }

    public List<File> findFiles(File startDir) {
        List<File> validFiles = new ArrayList<File>();

        File[] files = startDir.listFiles(new myFileFilter(extensions));
        for(File file : files) {
            validFiles.add(file);
        }
        File[] directories = startDir.listFiles(new myDirFilter());

        for(File dir : directories) {
            List<File> innerFiles = findFiles(dir);
            for(File innerFile : innerFiles){
                validFiles.add(innerFile);
            }
        }
        return validFiles;
    }

    private class myFileFilter implements FileFilter {
        private Pattern[] patterns;
        private Matcher matcher;

        private myFileFilter(String[] extensions) {
            patterns = new Pattern[extensions.length];
            for(int i = 0 ; i < extensions.length ; i++) {
                patterns[i] = Pattern.compile(".+\\." + extensions[i]);
            }
        }

        @Override
        public boolean accept(File file) {
            for(Pattern pattern : patterns) {
                matcher = pattern.matcher(file.getName());
                if (matcher.matches())
                    return true;
            }
            return false;
        }
    }

    private class myDirFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }
}