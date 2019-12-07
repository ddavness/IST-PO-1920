package m19.core.exception;

public class WorkNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 201912070441L;
    public WorkNotFoundException(int id) {
        super(id);
    }
}