package m19.core.notification;


import m19.core.Notification;
import m19.core.Work;

public class WorkRequestedNotification extends Notification {
    private static final long serialVersionUID = 201912070559L;
    public WorkRequestedNotification(Work work) {
        super("REQUISIÇÃO", work);
    }
}