package m19.core.userbehaviour;

import m19.core.Work;

/**
 * Normal
 */
public class Abiding extends UserBehaviour {
    static final long serialVersionUID = 201973109733703L;

    Abiding() {
        super("CUMPRIDOR", 5);
    }

    @Override
    public int getNumDays(Work work) {
        if (work.getNumberOfCopies() == 1)
            return 8;
        else if (work.getNumberOfCopies() <= 5)
            return 15;
        
        return 30;
    }

}