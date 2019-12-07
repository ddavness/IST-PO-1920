package m19.core;

/**
 * Notification - A string containing an header (depending on it's kind) and message.
 * Implemented according to teachers' UML.
 * @version 1.0
 */
public abstract class Notification {
    private final String _description;
    private final String _header;

    protected Notification(String header, String desc) {
        _header = header;
        _description = desc;
    }

    public String getMessage() {
        return _header + ": " + _description;
    }
}