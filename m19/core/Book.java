package m19.core;

import java.io.Serializable;

/**
 * Book
 * @@implNote ISBN is not validated.
 */
public class Book extends Work implements Serializable {


    static final long serialVersionUID = 6546513219879847L;
    private String _author;
    private String _isbn;

    public Book(
        int assignedId,
        String title,
        String author,
        int price,
        Category category,
        String isbn,
        int numberOfCopies) {

            super(assignedId, title, price, category, numberOfCopies);
            _author = author;
            _isbn = isbn;

    }

    public String getAuthor() {
        return _author;
    }

    private String getISBN() {
        return _isbn;
    }

    String getKindOfWork() {
        return "Livro";
    }

    String getExtraInfo() {
        return getAuthor() + " - " + getISBN();
    }

    
}