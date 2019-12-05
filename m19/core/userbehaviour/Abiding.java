package m19.core.userbehaviour;

import m19.core.Work;

/**
 * Normal
 */
public class Abiding extends UserBehaviour {

    Abiding() {
        super("CUMPRIDOR", 5);
    }

    @Override
    public int getNumDays(Work work) {
        if (work.getNumberAvailableCopies() == 1)
            return 8;
        else if (work.getNumberAvailableCopies() <= 5)
            return 15;
        
        return 30;
    }

    
}