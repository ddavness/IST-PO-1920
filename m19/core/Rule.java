package m19.core;
import m19.core.Library;
import m19.core.Request;

import java.io.Serializable;

/**
 * Rule
 */
public abstract class Rule implements Serializable {
    static final long serialVersionUID = 7310074210009L;
    private final int _id;
    protected final Library _library;
    public Rule(int id, Library library) {
        _id = id;
        _library = library;
    }
    /**
     *
     * @param user
     * @param work
     * @return true if the work can be requested by the user
     */
    public abstract boolean isValid(Request request);

    public int getID() {
        return _id;
    }

}