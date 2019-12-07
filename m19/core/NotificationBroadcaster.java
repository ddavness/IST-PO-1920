package m19.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class NotificationBroadcaster implements Serializable{
    private static final long serialVersionUID = 20191206447355L;

    private List<Notification> _notifications;
    private List<NotificationObserver> _observers;

    NotificationBroadcaster() {
        _notifications = new ArrayList<>();
        _observers = new ArrayList<>();
    }

    public void subscribe(NotificationObserver observer) {
        int thisIndex = _observers.indexOf(observer);
        if (thisIndex == -1) {
            _observers.add(observer);
        }
    }

    public void unsubscribe(NotificationObserver observer) {
        int thisIndex = _observers.indexOf(observer);
        if (thisIndex != -1) {
            _observers.remove(thisIndex);
        }
    }

    void broadcast() {
        // We can pass on the original array because we're replacing it
        for (NotificationObserver observer: _observers) {
            observer.notify(_notifications);
        }

        // "Flush" all notifications
        _notifications = new ArrayList<>();
    }

    void insertNotification(Notification notification) {
        _notifications.add(notification);
    }
}