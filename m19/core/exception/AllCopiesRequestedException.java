package m19.core.exception;

import m19.core.*;

public class AllCopiesRequestedException extends RuleNotSatisfiedException {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901201731L;
    private NotificationBroadcaster _notificationBroadcaster;

    public AllCopiesRequestedException(User user, Work work, int ruleId, NotificationBroadcaster broadcaster) {
        super(user, work, ruleId);
        _notificationBroadcaster = broadcaster;
    }

    public NotificationBroadcaster getNotificationBroadcaster() {
        return _notificationBroadcaster;
    }
}