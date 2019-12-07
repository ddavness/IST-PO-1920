package m19.core;

import java.io.Serializable;

/**
 * Request
 * @version 0.0
 * @implNote As per the teachers' UML.
 */
public class Request implements Serializable {

    static final long serialVersionUID = 201901093213216587L;

    private int _dailyFine = 5;
    private User _user;
    private Work _work;
    private int _returnDate;

    public Request(User user, Work work, int returnDate) {
        _user = user;
        _work = work;
        _returnDate = returnDate;
    }

    public boolean isPastDueDate() {
        return _work.getLibrary().getCurrentDate() > _returnDate;
    }

    public int getFine() {
        if (isPastDueDate()) {
            return _dailyFine * (_work.getLibrary().getCurrentDate() - _returnDate);
        }
        return 0;
    }

    /**
     * 
     * @param request the other request to check if they are equal
     * @return requests are equal iff they have the same user and work
     */
    public boolean equals(Request request) {
        return _user.equals(request._user) && _work.equals(request._work);
    }

    public User getUser() {
        return _user;
    }

    public Work getWork() {
        return _work;
    }

    /**
     * 
     * @return the day User has to return Work
     */
    public int getReturnDate() {
        return _returnDate;
    }
}