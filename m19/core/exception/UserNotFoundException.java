package m19.core.exception;

public class UserNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 201912070435L;
    public UserNotFoundException(int id) {
        super(id);
    }
}