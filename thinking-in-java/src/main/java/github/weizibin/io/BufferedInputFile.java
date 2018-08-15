package github.weizibin.io;

import java.io.*;

public class BufferedInputFile {

    public static String read(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s + "\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("README.md"));
    }

}
