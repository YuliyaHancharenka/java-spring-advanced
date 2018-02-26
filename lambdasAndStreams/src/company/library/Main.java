package company.library;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

public class Main {

    public static void main(String[] args) {

        Author[] authors = createAuthors();
        Book[] books = createBooks();

        Arrays
                .stream(authors)
                .forEach(author -> author
                        .setBooks(asList(Arrays.copyOfRange(books, 0, ThreadLocalRandom.current()
                                .nextInt(1, 9)))));
        Arrays
                .stream(books)
                .forEach(book -> book
                        .setAuthors(asList(Arrays.copyOfRange(authors, 0, ThreadLocalRandom.current()
                                .nextInt(1, 8)))));

        System.out.println("\nCheck if some/all book(s) have more than 200 pages:");
        Arrays.stream(books)
                .filter(book -> book.getNumberOfPages() > 200)
                .forEach(System.out::println);

        System.out.println("\nMax number of pages: " + Arrays
                .stream(books)
                .mapToInt(Book::getNumberOfPages)//todo
                .max());

        System.out.println("\nMin number of pages: " + Arrays
                .stream(books)
                .min(comparing(Book::getNumberOfPages)));

        System.out.println("\nFilter books with only single author:");
        Arrays
                .stream(books)
                .filter(book -> book.getAuthors().size() == 1)
                .forEach(System.out::println);

        System.out.println("\nSort the books by number of pages:");//todo
        Arrays
                .stream(books)
                .parallel()
                .sorted(comparing(Book::getNumberOfPages))
                .forEach(System.out::println);

        System.out.println("\nSort the books by title:");
        Arrays
                .stream(books)
                .parallel()
                .sorted(comparing(Book::getTitle))
                .forEach(System.out::println);

        System.out.println("\nGet list of all titles:");
        List<String> titlesList = Arrays
                .stream(books)
                .map(Book::getTitle)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println("\nPrint them using forEach method:");
        titlesList.forEach(System.out::println);

        System.out.println("\nGet distinct list of all authors:");
        Arrays
                .stream(books)
                .map(Book::getAuthors)
                .flatMap(authorsDistinct -> Arrays.stream(authors))//todo
                .distinct()
                .forEach(System.out::println);

        System.out.println("\nUse the Option type for determining the title of the biggest book of some author: ");
        Arrays
                .stream(books)
                .max(comparing(Book::getNumberOfPages))
                .ifPresent(book -> System.out.println("Book with title " + book.getTitle() + ", with "
                        + book.getNumberOfPages() + " pages"));
    }

    public static Book[] createBooks() {
        return new Book[]{
                new Book("Java programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("PHP programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("Delphi programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("Scala programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("C# programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("Kotlin programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("Groovy programming", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("Java programming advanced", ThreadLocalRandom.current().nextInt(80, 800)),
                new Book("Java programming professional", ThreadLocalRandom.current().nextInt(80, 800)),
        };
    }

    public static Author[] createAuthors() {
        return new Author[]{
                new Author("Yuliya", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Petya", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Vasya", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Vanya", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Aliaksei", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Vova", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Valik", (short) ThreadLocalRandom.current().nextInt(10, 80)),
                new Author("Maksim", (short) ThreadLocalRandom.current().nextInt(10, 80)),
        };
    }
}
