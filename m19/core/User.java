package m19.core;

import java.io.Serializable;
import java.util.*;

import m19.core.userbehaviour.*;
import m19.core.notification.*;
import m19.core.exception.RequestNotFoundException;

/**
 * User - User of the Library.
 * By default users have UserBehaviour NORMAL.
 * 
 * @version 0.1
 */
public class User implements Serializable, Comparable<User>, NotificationObserver {
    private static final long serialVersionUID = 20190110170235409L;

    private final int _id;

    private String _name;
    private String _email;

    private boolean _isActive;
    private UserBehaviour _behaviour;

    private Map<Work, Request> _requests;
    private List<Notification> _notifications;
    private int _accruedFine; // Total fine to pay, initialized at 0.
    private int _karma;

    public User(int assignedId, String name, String email) throws IllegalArgumentException {

        if (
            name == null ||
            email == null ||
            name.trim().isEmpty() ||
            email.trim().isEmpty()
        ) {
            throw new IllegalArgumentException("User name or email is empty!");
        }

        _name = name;
        _email = email;
        _isActive = true;

        _behaviour = new Normal();

        _id = assignedId;

        _accruedFine = 0;

        _requests = new LinkedHashMap<>();
        _notifications = new ArrayList<>();
    }

    public boolean isActive() {
        return _isActive;
    }

    void setActive(boolean active) {
        _isActive = active;
    }

    public String getDescription() {
        if (_isActive) {
            return "" + _id + " - " + _name + " - " + _email + " - " + _behaviour + " - ACTIVO";
        } else {
            return "" + _id + " - " + _name + " - " + _email + " - " + _behaviour + " - SUSPENSO - EUR " + _accruedFine;
        }
    }

    public Collection<Notification> getNotifications() {
        // Clear notifications
        Collection<Notification> notifs = _notifications;
        _notifications = new ArrayList<>();
        return notifs;
    }

    public void notify(Collection<Notification> notifications) {
        for (Notification n: notifications) {
            _notifications.add(n);
        }
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getEmail() {
        return _email;
    }

    /**
     * 
     * @param user
     * @return Ordem lexicografica, e depois ordem dos Ids se os nomes forem
     * iguais
     */
    public int compareTo(User user) {
        return getName().compareTo(user.getName()) == 0? getId() - user.getId(): getName().compareTo(user.getName());
    }

    public boolean equals(User user) {
        return getId() == user.getId();
    }

    public Collection<Request> getAllRequests() {
        // return Collections.unmodifiableList(_requests);
        return _requests.values();
    }

    public UserBehaviour getBehaviour() {
        return _behaviour;
    }

    /**
     * 
     * @param request is not validated
     */
    void addRequest(Request request) {
        _requests.put(request.getWork(), request);
    }

    public void returnWork(Request request) {
        Work work = request.getWork();
        work.processReturnFrom(this);
        _requests.remove(work);
        _accruedFine += request.getFine();

        // Recalculate karma and update status
        _behaviour = getBehaviour().updateKarma(this, request.isPastDueDate());

        NotificationBroadcaster notif = work.getReturnNotificationBroadcaster();
        notif.insertNotification(new WorkReturnedNotification(work));
        notif.broadcast();
    }

    void clearFine() {
        _accruedFine = 0;
    }

    public int getKarma() {
        return _karma;
    }

    void setKarma(int karma) {
        _karma = karma;
    }

    /**
     * 
     * @param work
     * @return true iff the user has requested the work received in argument
     */
    public Request requestedWork(Work work) throws RequestNotFoundException {
        Request found = _requests.get(work);
        if (found == null) {
            throw new RequestNotFoundException(work.getId(), getId());
        }

        return found;
    }

    /**
     * 
     * @return total fine user has accumulated
     */
    public int getAccruedFine() {
        return _accruedFine;
    }
}