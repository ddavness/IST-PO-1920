package m19.core;

/**
 * Notification - Is just a simple String.
 * Implemented according to teachers' UML.
 * @version 0.0
 * //FIXME Maybe implement kind of notification?
 */
public class Notification {

    private final String _message;

    public Notification(String message) {
        _message = message;
    }

    public String getMessage() {
        return _message;
    }
}