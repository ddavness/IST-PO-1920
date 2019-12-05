package m19.core.exception;

import m19.core.*;

public class AllCopiesRequestedException extends RuleNotSatisfiedException {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901201731L;

    public AllCopiesRequestedException(User user, Work work, int ruleId) {
        super(user, work, ruleId);
    }

}