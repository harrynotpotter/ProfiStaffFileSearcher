import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainApp {
    static List<File> fileList;

    public static void main(String[] args) {
        fileList = new ArrayList<>();
        File directory = new File("test1");
        findFiles(directory);
        Collections.sort(fileList, Comparator.comparing(File::getName));
        write(fileList);
    }

    public static void findFiles(File directory) {

        if (directory.isDirectory()) {
            final File[] files = directory.listFiles();
            if (files != null) {
                for (File item : files) {
                    if (item.isDirectory()) {
                        findFiles(item);
                    }
                    if (item.isFile() && item.getName().toLowerCase().endsWith(".txt")) {
                        fileList.add(item);
                    }
                }
            }
        }
    }

    public static void write(List<File> fileList) {
        File newFile = new File("filetest.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
            for (File f : fileList) {
                BufferedReader br = new BufferedReader(new FileReader(f.toString()));
                while (br.ready()) {
                    bw.write(br.readLine());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
