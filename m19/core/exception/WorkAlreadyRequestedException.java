package m19.core.exception;

import m19.core.*;

public class WorkAlreadyRequestedException extends RuleNotSatisfiedException {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901201731L;

    public WorkAlreadyRequestedException(User user, Work work, int ruleId) {
        super(user, work, ruleId);
    }

}