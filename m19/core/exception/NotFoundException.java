package m19.core.exception;

public abstract class NotFoundException extends Exception {
    private static final long serialVersionUID = 201912070410L;
    private int _id;

    public NotFoundException(int id) {
        _id = id;
    }

    public int getRequestedId() {
        return _id;
    }
}