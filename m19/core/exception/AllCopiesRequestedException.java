package m19.core.exception;

import m19.core.*;

public class AllCopiesRequestedException extends RuleNotSatisfiedException {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901201731L;
    private Work _work;

    public AllCopiesRequestedException(User user, Work work, int ruleId) {
        super(user, work, ruleId);
        _work = work;
    }

    public NotificationBroadcaster getNotificationBroadcaster() {
        return _work.getReturnNotificationBroadcaster();
    }
}