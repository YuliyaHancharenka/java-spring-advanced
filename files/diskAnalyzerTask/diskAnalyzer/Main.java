package diskAnalyzerTask;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Consulting.proposeFunctions();
        int choice = 0;
        String dir;

        try {
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("No such an option");
            System.exit(1);
        }

        System.out.println("Please write directory:");

        try {
            Scanner scanner = new Scanner(System.in);
            dir = scanner.nextLine();
            Consulting.giveAnswer(choice, new File(dir));
        } catch (NoSuchElementException e) {
            System.out.println("No such an option");
        }
    }
}
