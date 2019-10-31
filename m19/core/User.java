package m19.core;

import java.util.ArrayList;

/**
 * User -  User of Mediateca.
 * By default users have UserBehaviour NORMAL.
 * 
 * @version 0.0
 * @implNote I haven't really tested it ....
 * @implNote //FIXEME Implement getDescriptions
 */
public class User {

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

        //FIXME Implement.
        return "PLACEHOLDER STRING"
    }

    protected ArrayList<Notification> getNotifications() {
        return _notifications;
    }



    
}