package m19.core.notification;
import m19.core.Notification;
import m19.core.Work;


public class WorkReturnedNotification extends Notification {
    public WorkReturnedNotification(Work work) {
        super("ENTREGA", work);
    }
}