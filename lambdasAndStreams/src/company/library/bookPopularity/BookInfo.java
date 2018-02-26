package company.library.bookPopularity;

public class BookInfo {

    private String title;
    private String author;
    private int bookQtyInLibrary;
    private int bookQtyInReadingRoom;
    private int bookQtyOnHands;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookQtyInLibrary() {
        return bookQtyInLibrary;
    }

    public void setBookQtyInLibrary(int bookQtyInLibrary) {
        this.bookQtyInLibrary = bookQtyInLibrary;
    }

    public int getBookQtyInReadingRoom() {
        return bookQtyInReadingRoom;
    }

    public void setBookQtyInReadingRoom(int bookQtyInReadingRoom) {
        this.bookQtyInReadingRoom = bookQtyInReadingRoom;
    }

    public int getBookQtyOnHands() {
        return bookQtyOnHands;
    }

    public void setBookQtyOnHands(int bookQtyOnHands) {
        this.bookQtyOnHands = bookQtyOnHands;
    }

    public BookInfo(String title, String author, int bookQtyInLibrary, int bookQtyInReadingRoom, int bookQtyOnHands) {
        this.title = title;
        this.author = author;
        this.bookQtyInLibrary = bookQtyInLibrary;
        this.bookQtyInReadingRoom = bookQtyInReadingRoom;
        this.bookQtyOnHands = bookQtyOnHands;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookQtyInLibrary=" + bookQtyInLibrary +
                ", bookQtyInReadingRoom=" + bookQtyInReadingRoom +
                ", bookQtyOnHands=" + bookQtyOnHands +
                '}';
    }
}
