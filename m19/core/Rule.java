package m19.core;
import m19.core.Library;
import m19.core.Request;
import m19.core.exception.RuleNotSatisfiedException;

import java.io.Serializable;

/**
 * Rule
 */
public abstract class Rule implements Serializable {
    static final long serialVersionUID = 7310074210009L;
    protected final Library _library;
    protected final int _ruleId;

    public Rule(int id, Library library) {
        _ruleId = id;
        _library = library;
    }

    /**
     *
     * @param user
     * @param work
     *
     */
    public abstract void check(Request request) throws RuleNotSatisfiedException;
}