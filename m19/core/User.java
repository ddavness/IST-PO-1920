package m19.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * User - User of the Library.
 * By default users have UserBehaviour NORMAL.
 * 
 * @version 0.0
 * @implNote I haven't really tested it ....
 * @implNote //FIXEME Implement getDescriptions
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    // FIXME: I cannot be public
    public static int _nextID; // initialized at 0
    private final int _id;

    private boolean _isActive;
    private String _name;
    private String _email;
    private UserBehaviour _userBehaviour;

    ArrayList<Request> _requests = new ArrayList<>();
    ArrayList<Notification> _notifications = new ArrayList<>();

    public User(String name, String email) {
        _name = name;
        _email = email;
        _isActive = true;

        _userBehaviour = UserBehaviour.NORMAL;

        _id = _nextID++;
    }

    protected boolean isActive() {
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

    protected ArrayList<Notification> getNotifications() {
        return _notifications;
    }
}