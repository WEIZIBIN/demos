package github.weizibin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {

    public static void main(String[] args) {
        printFileNameIn(".");
    }

    public static void printFileNameIn(String path) {
        List<String> fileNameList = new ArrayList<>();
        File file = new File(path);
        recurseDirectory(fileNameList, file);
        for (String fileNames: fileNameList) {
            System.out.println(fileNames);
        }
    }

    private static void recurseDirectory(List<String> fileNameList, File current) {
        File[] files = current.listFiles();
        for (File file: files) {
            if (file.isDirectory()) {
                recurseDirectory(fileNameList, file);
            } else {
                fileNameList.add(file.getName());
            }
        }
    }

}
