package m19.core;

import java.io.Serializable;

/**
 * Dvd
 */
public class Dvd extends Work implements Serializable {

    static final long serialVersionUID = 2718290452398765651L;
    private String _director;
    private String _igac;


    public Dvd(
        int assignedId,
        Library library,
        String title,
        String director,
        int price,
        Category category,
        String igac,
        int numberOfCopies) {

            super(assignedId, library, title, price, category, numberOfCopies);
            _director = director;
            _igac = igac;

    }

    public String getDirector() {
        return _director;
    }

    private String getIGAC() {
        return _igac;
    }

    String getKindOfWork() {
        return "DVD";
    }

    String getExtraInfo() {
        return getDirector() + " - " + getIGAC();
    }

    public boolean search(String term) {
        return getDirector().toLowerCase().contains(term.toLowerCase()) ||
        getTitle().toLowerCase().contains(term.toLowerCase());
    }


}