package m19.core;

/**
 * Dvd
 */
public class Dvd extends Work {
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