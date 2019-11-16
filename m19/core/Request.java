package m19.core;

/**
 * Request
 * @version 0.0
 * @implNote As per the teachers' UML.
 */
public class Request {
    private static int _dailyFine = 5;
    private User _user;
    private int _deadline;
    private int _accruedFine;
    private boolean _returned;
    private Work _work;

    public Request(User user, Work work, int length) {
        _user = user;
        _deadline = length;
        _accruedFine = 0;
        _returned = false;
        _work = work;
    }

    public int getDeadine() {
        return _deadline;
    }

    public int getFine() {
        return _accruedFine;
    }

    public void updateStatus() {
        // FIXME: Placeholder. Update.
        int date = 0;

        if (date > _deadline && !_returned) {
            _accruedFine = (date - _deadline) * _dailyFine;
            _user.setActive(false);
        }
    }

    /**
     * Returns the work.
     * @return
     */
    public boolean returnWork() {
        _returned = true;

        return _accruedFine == 0;
    }

    /**
     * 
     * @param request the other request to check if they are equal
     * @return requests are equal iff they have the same user and work
     */
    public boolean equals(Request request) {
        return _user.equals(request._user) && _work.equals(request._work);
    }
}