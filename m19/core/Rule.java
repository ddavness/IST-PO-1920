package m19.core;
import m19.core.Library;
import m19.core.Request;

/**
 * Rule
 */
public abstract class Rule {
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
     * @return true iff the work can be requested by the user
     */
    public abstract boolean isValid(Request request);


}