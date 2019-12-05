package m19.core.userbehaviour;

import java.io.Serializable;
import m19.core.Work;

/**
 * UserBehaviour
 */
public abstract class UserBehaviour implements Serializable {

    static final long serialVersionUID = 20197310973373L;

    private final String _name;
    private int _maxReqWorks;

    /**
     * 
     * @param name Name in portuguese of the specific user behaviour
     * @param maxReqWorks Maximum number of works a kind of user may request
     */
    UserBehaviour(String name, int maxReqWorks) {
        _name = name;
        _maxReqWorks = maxReqWorks;

    }

    public String getDescription() {
        return _name;
    }

    public abstract int getNumDays(Work work);

    public int maxNumberOfRequestedWorks() {
        return _maxReqWorks;
    }

    
}