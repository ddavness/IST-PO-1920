package m19.core;

/**
 * Request
 * @version 0.0
 * @implNote As per the teachers' UML.
 */
public class Request {

    private int _deadline;


    public Request(int deadline) {
        _deadline = deadline;
    }


    public int getDeadine() {
        return _deadline;
    }
}