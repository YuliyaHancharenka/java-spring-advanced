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
        booksList.forEach(b -> System.out.println(getPopularity(b)));
    }

    public static double getPopularity(BookInfo bookInfo) {
        double popularity = bookInfo.getBookQtyOnHands()
                / (bookInfo.getBookQtyInLibrary() + bookInfo.getBookQtyInReadingRoom());
        if (popularity > 0.5) {
            System.out.println(popularity + " - It is a popular book");
        } else {
            System.out.println(popularity + " - It is not a popular book");
        }
        return popularity;
    }
}
