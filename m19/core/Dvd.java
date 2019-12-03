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
        int assignedID,
        String title,
        String director,
        int price,
        Category category,
        String igac,
        int numberOfCopies) {

            super(assignedID, title, price, category, numberOfCopies);
            _director = director;
            _igac = igac;

    }

    public String getDirector() {
        return _director;
    }

    private String getIGAC() {
        return _igac;
    }


    public String getDescription() {
        String extraInfo = getDirector() + " - " + getIGAC();
        return getDescription("DVD", extraInfo);
    }

}