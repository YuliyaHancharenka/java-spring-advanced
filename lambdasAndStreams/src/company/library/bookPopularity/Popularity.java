package company.library.bookPopularity;

@FunctionalInterface
public interface Popularity {

    double calculatePopularity(int bookQtyInLibrary, int bookQtyInReadingRoom, int bookQtyOnHands);
}
