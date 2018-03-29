package diskAnalyzerTask;

import java.io.File;

import static diskAnalyzerTask.DiskAnalyzer.*;

public class Consulting {

    public static void proposeFunctions() {
        System.out.println("\nHi, you're using Disk Analyzer. \n\nPlease select function and press Enter:" +
                "\n1 - Search file with max quantity of letter 's' in file name and its path" +
                "\n2 - Average size of file in defined directory or subdirectory" +
                "\n3 - Top-5 the biggest size files" +
                "\n4 - Quantity of files and directories for the first alphabet letters");
    }

    public static void giveAnswer(int choice, File dir) {
        switch (choice) {
            case 1:
                //Search file with max quantity of letter 's' in file name and its path
                getFileNameWithMaxLetterCount('s', dir);
                break;
            case 2:
                //Average size of file in defined directory or subdirectory
                System.out.println("\nAverage size of file in defined directory or subdirectory: " + getFileAverageSize(dir));
                break;
            case 3:
                //Top-5 the biggest size files
                System.out.println("\nTop-5 the biggest size files:");
                getTop5FilesSize(dir).forEach(file -> {
                    System.out.println("File: " + file.getAbsolutePath() + " | size: " + file.length());
                });
                break;
            case 4:
                //Quantity of files and directories for the first alphabet letters (f.ex, letter A – 100 000 files and и 200 directories)
                System.out.println("\nQuantity of files and directories for the first alphabet letters:");
                getQtyFilesAndDirsStartsByLetter(dir);
                break;
            default:
                System.out.println("No such an option!");
                break;
        }
    }
}
