package m19.core.exception;

import m19.core.Rule;
import m19.core.User;
import m19.core.Work;

/**
 * Defines an exception that is thrown when a given rule for a request is not satisfied
 */

public class RuleNotSatisfiedException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101701L;

    private int _violatedRuleID;
    private User _user;
    private Work _work;

    public RuleNotSatisfiedException(User user, Work work, int ruleID) {
        _violatedRuleID = ruleID;
        _user = user;
        _work = work;
    }

    public int getUserId() {
        return _user.getID();
    }

    public int getWorkId() {
        return _work.getID();
    }

    public int getViolatedRule() {
        return _violatedRuleID;
    }

}