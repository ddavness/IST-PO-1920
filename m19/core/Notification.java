package m19.core;
import m19.core.Work;

/**
 * Notification - A string containing an header (depending on it's kind) and message.
 * Implemented according to teachers' UML.
 * @version 1.0
 */
public abstract class Notification {
    private final Work _work;
    private final String _header;

    protected Notification(String header, Work work) {
        _header = header;
        _work = work;
    }

    public String getMessage() {
        return _header + ": " + _work.getDescription();
    }

    public Work getWork() {
        return _work;
    }
}