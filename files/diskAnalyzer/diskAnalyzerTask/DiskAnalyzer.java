package diskAnalyzerTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiskAnalyzer {

    private static List<File> retrieveFiles(File dir) {
        List<File> filesList = new ArrayList<>();
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    filesList.add(file);
                    if (file.isDirectory()) {
                        filesList.addAll(retrieveFiles(file));
                    }
                }
            }
        }
        return filesList.stream()
                .filter(f -> !f.isDirectory())
                .collect(Collectors.toList());
    }

    private static List<File> retrieveDirectories(File dir) {
        List<File> filesList = new ArrayList<>();
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    filesList.add(file);
                    if (file.isDirectory()) {
                        filesList.addAll(retrieveFiles(file));
                    }
                }
            }
        }
        return filesList.stream()
                .filter(File::isDirectory)
                .collect(Collectors.toList());
    }

    static List<File> getTop5FilesSize(File dir) {
        List<File> filesList = retrieveFiles(dir);
        return filesList
                .stream()
                .filter(file -> !file.isDirectory())
                .sorted(Comparator.comparingLong(File::length).reversed())
                .limit(5).collect(Collectors.toList());
    }

    static Double getFileAverageSize(File dir) {
        List<File> files = retrieveFiles(dir);
        return files
                .stream()
                .filter(file -> !file.isDirectory())
                .mapToLong(File::length)
                .average().getAsDouble();
    }

    static void getFileNameWithMaxLetterCount(char character, File dir) {
        int max = 0;
        int count;
        String path = null;
        List<File> filesList = retrieveFiles(dir);
        for (File file : filesList) {
            if (!file.isDirectory()) {
                String fileName = file.getName();
                count = 0;
                for (int i = 0; i < fileName.length(); i++) {
                    if (fileName.charAt(i) == character) {
                        count++;
                    }
                }
                if (count > max) {
                    max = count;
                    path = file.getAbsolutePath() + "\\" + fileName;
                }
            }
        }
        System.out.println("\nFile name and path with the max letter 's' count: quantity - " + max + ", path - " + path);
    }

    static void getQtyFilesAndDirsStartsByLetter(File dir) {
        List<File> directoriesList = retrieveDirectories(dir);
        List<String> abc = List.of("A", "B", "C");
        abc.forEach(letter -> {
            long countDirs = directoriesList
                    .stream()
                    .filter(File::isDirectory)
                    .filter(file -> {
                        return file.getName().startsWith(letter);
                    }).count();

            List<File> filesList = retrieveFiles(dir);
            long countFiles = filesList
                    .stream()
                    .filter(file -> !file.isDirectory())
                    .filter(file -> {
                        return file.getName().startsWith(letter);
                    }).count();

            System.out.println(letter + " - dirs: " + countDirs + " | files: " + countFiles);
        });
    }
}
