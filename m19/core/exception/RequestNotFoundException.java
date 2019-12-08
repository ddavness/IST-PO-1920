package m19.core.exception;

public class RequestNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 201912070435L;
    private int _userId;

    public RequestNotFoundException(int wid, int uid) {
        super(wid);
        _userId = uid;
    }

    public int getWorkId() {
        return getRequestedId();
    }

    public int getUserId() {
        return _userId;
    }
}