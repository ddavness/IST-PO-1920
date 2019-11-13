package m19.core;

/**
 * Work - Abstract class which can be a Book or DVD.
 * @version 0.0
 * @@implSpec Auto generates workID using a static attribute
 * 
 */
public abstract class Work {

    private static int _nextWorkID;

    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;

    private Category _category; //FIXME May have more than one category

    public Work(String title, int price, Category category, int numberOfCopies) {
        _id = _nextWorkID++;
        _price = price;
        _numberOfCopies = numberOfCopies;
        _title = title;
    }


    /**
     * 
     * @return a standardized String representation to be sent to user.
     */
    public abstract String getDescription();

    
}