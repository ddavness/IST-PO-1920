package m19.core.notification;
import m19.core.Notification;

public class WorkRequestedNotification extends Notification{
    public WorkRequestedNotification(String desc) {
        super("REQUISIÇÃO", desc);
    }
}