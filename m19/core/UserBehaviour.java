package m19.core;

/**
 * UserBehaviour There 3 types of user behaviour defined in the UML.
 */

public enum UserBehaviour {
    NORMAL(2),
    CUMPRIDOR(3),
    FALTOSO(5);

    private int _daysAllowed;
    private UserBehaviour(int days) {
        _daysAllowed = days;
    }

    public int getDaysAllowed() {
        return _daysAllowed;
    }
}