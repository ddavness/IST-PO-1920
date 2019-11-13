package m19.core;

/**
 * Book
 * @@implNote ISBN is not validated.
 */
public class Book extends Work {

    private String _author;
    private String _isbn;

    public Book(
        String title,
        String author,
        int price,
        Category category,
        String isbn,
        int numberOfCopies) {

            super(title, price, category, numberOfCopies);
            _author = author;
            _isbn = isbn;

    }

    public String getDescription() {
        //FIXME Implement
        return "PLACEHOLDER";
    }
    
}