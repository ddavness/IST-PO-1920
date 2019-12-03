package m19.core;

import java.io.Serializable;
import java.util.*;
import m19.core.Request;

/**
 * Work - Abstract class which can be a Book or Dvd.
 * @version 0.0
 * @@implSpec Auto generates _workId using a static attribute
 * 
 */
public abstract class Work implements Serializable{

    static final long serialVersionUID = 7310074210009L;

    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private List<Request> _requests;

    private Category _category; //FIXME May have more than one category

    public Work(int assignedID, String title, int price, Category category, int numberOfCopies) {
        _id = assignedID;
        _price = price;
        _category = category;
        _numberOfCopies = numberOfCopies;
        _title = title;
        _requests = new ArrayList<>();
    }


    /**
     * 
     * @return a standardized String representation to be sent to user.
     */
    public abstract String getDescription();

    /**
     * 
     * @param kindOfWork If it is a Book or Dvd for example.
     * @param extraInfo Author or director of work (when appliable)
     * @return Formated string for 4.3.1 of problem statement.
     */

    public String getDescription(String kindOfWork, String extraInfo) {
        String WORK_REPR_FORMAT = "%d - %d de %d - %s - %s - %d - %s - %s";
        return String.format(WORK_REPR_FORMAT,
        getID(),
        getNumberAvailableCopies(),
        getNumberOfCopies(),
        kindOfWork,
        getTitle(),
        getPrice(),
        getCategory(),
        extraInfo);
    }

    public int getID() {
        return _id;
    }

    public String getTitle() {
        return _title;
    }

    public int getPrice() {
        return _price;
    }

    public int getNumberOfCopies() {
        return _numberOfCopies;
    }

    public Category getCategory() {
        return _category;
    }


    public int getNumberAvailableCopies() {
        return getNumberOfCopies() - _requests.size();
    }

    public boolean equals(Work work) {
        return getID() == work.getID();
    }

    /**
     * 
     * @param request is not validated
     */
    public void addRequest(Request request) {
        _requests.add(request);
    }

}