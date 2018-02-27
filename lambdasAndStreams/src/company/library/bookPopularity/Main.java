package company.library.bookPopularity;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookInfo javaBook = new BookInfo("Thinking in Java", "Bruce Eckel",
                100, 50, 90);
        BookInfo phpBook = new BookInfo("PHP programming", "Author",
                80, 30, 10);
        BookInfo groovyBook = new BookInfo("Groovy programming", "Author",
                110, 50, 100);
        List<BookInfo> booksList = new ArrayList<>();
        booksList.add(javaBook);
        booksList.add(phpBook);
        booksList.add(groovyBook);
        booksList.forEach(b -> popularity
                .calculatePopularity(b.getBookQtyInLibrary(), b.getBookQtyInReadingRoom(), b.getBookQtyOnHands()));
    }

    private static Popularity popularity = (bookQtyInLibrary, bookQtyInReadingRoom, bookQtyOnHands) -> {
        double popularity = (double) bookQtyOnHands / (bookQtyInLibrary + bookQtyInReadingRoom);
        if (popularity > 0.5) {
            System.out.println(String.format("%.2f", popularity) + " - It is a popular book");
        } else {
            System.out.println(String.format("%.2f", popularity) + " - It is not a popular book");
        }
        return popularity;
    };
}
