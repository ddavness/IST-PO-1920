package m19.core;
import java.io.Serializable;
import java.util.Collection;

/**
 * 
 */
public interface NotificationObserver extends Serializable {
    public void notify(Collection<Notification> notifications);
}