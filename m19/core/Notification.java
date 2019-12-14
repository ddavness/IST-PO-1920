package m19.core;
import java.io.Serializable;

/**
 * Notification - A string containing an header (depending on it's kind) and message.
 * Implemented according to teachers' UML.
 * @version 1.0
 */
public abstract class Notification implements Serializable {
    private static final long serialVersionUID = 201912070558L;
    private final String _description;
    private final String _header;

    public Notification(String header, String desc) {
        _header = header;
        _description = desc;
    }

    public String getMessage() {
        return _header + ": " + _description;
    }
}