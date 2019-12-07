package m19.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.List;

import m19.core.userbehaviour.*;

/**
 * User - User of the Library.
 * By default users have UserBehaviour NORMAL.
 * 
 * @version 0.1
 * @implNote I haven't really tested it ....
 * @implNote //FIXEME Implement getDescriptions
 */
public class User implements Serializable, Comparable<User>, NotificationObserver {
    private static final long serialVersionUID = 20190110170235409L;

    private final int _id;

    private String _name;
    private String _email;

    private boolean _isActive;
    private UserBehaviour _behaviour;

    private HashMap<Work, Request> _requests;
    private List<Notification> _notifications = new ArrayList<>();
    private int _accruedFine; // Total fine to pay, initialized at 0.

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
    }

    public boolean isActive() {
        return _isActive;
    }

    void setActive(boolean active) {
        _isActive = active;
    }

    public String getDescription() {
        if (_isActive) {
            return "" + _id + " - " + _name + " - " + _email + " - " + _behaviour.getDescription() + " - ACTIVO";
        } else {
            int totalFines = 0;
            for (Request req : _requests.values()) {
                totalFines += req.getFine();
            }
            return "" + _id + " - " + _name + " - " + _email + " - " + _behaviour + " - SUSPENSO - EUR " + totalFines;
        }
    }

    public List<Notification> getNotifications() {
        List<Notification> notifs = _notifications;
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
    public void addRequest(Request request) {
        _requests.put(request.getWork(), request);
    }

    /**
     * 
     * @param work
     * @return true iff the user has requested the work received in argument
     */
    public boolean hasRequestedWork(Work work) {
        for (Request req: getAllRequests())
            if (req.getWork().equals(work))
                return true;
        return false;
    }

    /**
     * 
     * @return total fine user has accumulated
     */
    public int getAccruedFine() {
        return _accruedFine;
    }
}