package m19.core;

import java.io.Serializable;

/**
 * DVD
 */
public class DVD extends Work implements Serializable {

    static final long serialVersionUID = 2718290452398765651L;
    private String _director;
    private String _igac;


    public DVD(
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