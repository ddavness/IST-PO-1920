package m19.core;

import java.io.Serializable;

/**
 * Dvd
 */
public class Dvd extends Work implements Serializable {

    final long serialVersionUID = 2718290452398765651L;
    private String _director;
    private String _igac;


    public Dvd(
        String title,
        String director,
        int price,
        Category category,
        String igac,
        int numberOfCopies) {

            super(title, price, category, numberOfCopies);
            _director = director;
            _igac = igac;

    }


    public String getDescription() {
        return "PLACEHOLDER";
        //FIXME Implement
    }
    
}