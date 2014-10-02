package owl.me;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public final class FileFinder {
    private String pattern;

    public FileFinder(String[] extensions) {
        StringBuilder sb = new StringBuilder(".+\\.(");
        for(String extension : extensions) {
            sb.append(extension);
            sb.append('|');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        pattern = new String(sb);
    }

    public List<Path> findFiles(Path startDir) {
        List<Path> validFiles = new ArrayList<>();
        List<Path> directories = new ArrayList<>();

        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(startDir)) {
            for(Path path : directoryStream) {
                if(Files.exists(path))
                    if (Files.isDirectory(path))
                        directories.add(path);
                    else if (Files.isReadable(path) && path.getFileName().toString().matches(pattern))
                        validFiles.add(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Path dir : directories) {
            List<Path> innerFiles = findFiles(dir);
            for(Path innerFile : innerFiles){
                validFiles.add(innerFile);
            }
        }
        return validFiles;
    }
/*
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
*/
/*
    private class myDirFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }
*/
}