package m19.core.notification;

import m19.core.Notification;
import m19.core.Work;


public class WorkReturnedNotification extends Notification {
    private static final long serialVersionUID = 201912070604L;
    public WorkReturnedNotification(Work work) {
        super("ENTREGA", work.getDescription());
    }
}