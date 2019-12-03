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


    abstract String getKindOfWork();
    abstract String getExtraInfo();

    /**
     * 
     * @return a standardized String representation to be sent to user.
     * @@implNote Uses Template Factory design pattern.
     */
    public String getDescription() {
        String WORK_REPR_FORMAT = "%d - %d de %d - %s - %s - %d - %s - %s";
        return String.format(WORK_REPR_FORMAT,
        getID(),
        getNumberAvailableCopies(),
        getNumberOfCopies(),
        getKindOfWork(),
        getTitle(),
        getPrice(),
        getCategory(),
        getExtraInfo());
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