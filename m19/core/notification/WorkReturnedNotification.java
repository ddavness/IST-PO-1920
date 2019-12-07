package m19.core.notification;
import m19.core.Notification;

public class WorkReturnedNotification extends Notification {
    public WorkReturnedNotification(String desc) {
        super("ENTREGA", desc);
    }
}