package m19.core;

import java.io.Serializable;
import m19.core.Work;

/**
 * UserBehaviour
 */
public abstract class UserBehaviour implements Serializable {

    static final long serialVersionUID = 20197310973373L;

    private final String _name;
    private int _maxReqWorks;
    private int _maxWorkValue;

    /**
     * 
     * @param name Name in portuguese of the specific user behaviour
     * @param maxReqWorks Maximum number of works a kind of user may request
     * @param maxWorkValue Maximum value of the work that can be borrowed
     */
    protected UserBehaviour(String name, int maxReqWorks, int maxWorkValue) {
        _name = name;
        _maxReqWorks = maxReqWorks;
        _maxWorkValue = maxWorkValue;
    }

    @Override
    public String toString() {
        return _name;
    }

    public abstract int getNumDays(Work work);

    public abstract UserBehaviour updateKarma(User user, boolean lateReturn);

    public int maxNumberOfRequestedWorks() {
        return _maxReqWorks;
    }

    public int maximumWorkValueBorrowable() {
        return _maxWorkValue;
    }

    protected void setUserKarma(User user, int karma) {
        user.setKarma(karma);
    }

}