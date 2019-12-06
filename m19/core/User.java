package m19.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 20190110170235409L;

    private final int _id;

    private String _name;
    private String _email;

    private boolean _isActive;
    private UserBehaviour _behaviour;

    private List<Request> _requests = new ArrayList<>();
    private List<Notification> _notifications = new ArrayList<>();

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
            for (Request req : _requests) {
                totalFines += req.getFine();
            }
            return "" + _id + " - " + _name + " - " + _email + " - " + _behaviour + " - SUSPENSO - EUR " + totalFines;
        }
    }

     public List<Notification> getNotifications() {
        return _notifications;
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

    public List<Request> getAllRequests() {
        return Collections.unmodifiableList(_requests);
    }

    public UserBehaviour getBehaviour() {
        return _behaviour;
    }

    /**
     * 
     * @param request is not validated
     */
    public void addRequest(Request request) {
        _requests.add(request);
    }
}