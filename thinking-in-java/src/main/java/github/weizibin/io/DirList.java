package github.weizibin.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list = path.list(new DirFilter("thinking-in-java"));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String filePath: list) {
            System.out.println(filePath);
        }
    }

    // Creation of anonymous inner class
    public static FilenameFilter filter (final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }
}

// Creation of outer class
class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
