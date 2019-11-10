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

    public static int _nextID; /* inicialized at 0 */
    private final int _id;
    private boolean _isActive;
    private String _name;
    private String _email;
    ArrayList<Notification> _notifications = new ArrayList<>();

    private UserBehaviour _userBehaviour;


    ArrayList<Request> _requests = new ArrayList<>();

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

    public String getDescription() {
        if (_isActive) {
            return "" + _id + " - " + _name + " - " + _email + " - " + _userBehaviour + " - ACTIVO";
        } else {
            return "" + _id + " - " + _name + " - " + _email + " - " + _userBehaviour + " - SUSPENSO";
        }
    }

    protected ArrayList<Notification> getNotifications() {
        return _notifications;
    }
}