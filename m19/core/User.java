package m19.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import m19.core.exception.InvalidArgumentException;

/**
 * User - User of the Library.
 * By default users have UserBehaviour NORMAL.
 * 
 * @version 0.0
 * @implNote I haven't really tested it ....
 * @implNote //FIXEME Implement getDescriptions
 */
public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 1L;

    private static int _nextID; // initialized at 0
    private final int _id;

    private String _name;
    private String _email;

    private boolean _isActive;
    private UserBehaviour _userBehaviour;

    private List<Request> _requests = new ArrayList<>();
    private List<Notification> _notifications = new ArrayList<>();

    public User(String name, String email) throws InvalidArgumentException {

        if (name.trim().isEmpty() || email.trim().isEmpty())
            throw new InvalidArgumentException(name, email);

        _name = name;
        _email = email;
        _isActive = true;

        _userBehaviour = UserBehaviour.NORMAL;

        _id = _nextID++;
    }

    public boolean isActive() {
        return _isActive;
    }

    void setActive(boolean active) {
        _isActive = active;
    }

    public String getDescription() {
        if (_isActive) {
            return "" + _id + " - " + _name + " - " + _email + " - " + _userBehaviour + " - ACTIVO";
        } else {
            int totalFines = 0;
            for (Request req : _requests) {
                totalFines += req.getFine();
            }
            return "" + _id + " - " + _name + " - " + _email + " - " + _userBehaviour + " - SUSPENSO - EUR " + totalFines;
        }
    }

    protected List<Notification> getNotifications() {
        return _notifications;
    }

    public int getID() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    /**
     * 
     * @param user
     * @return Ordem lexicografica, e depois ordem dos IDs se os nomes forem
     * iguais
     */
    public int compareTo(User user) {
        return getName().compareTo(user.getName()) == 0? 0 : getID() - user.getID();
    }

    public boolean equals(User user) {
        return getID() == user.getID();
    }

    public List<Request> getAllRequests() {
        return Collections.unmodifiableList(_requests);
    }

    public UserBehaviour getBehaviour() {
        return _userBehaviour;
    }

    /**
     * 
     * @param request is not validated
     */
    public void addRequest(Request request) {
        _requests.add(request);
    }
}