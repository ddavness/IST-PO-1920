package m19.core.userbehaviour;

import m19.core.Work;

/**
 * Normal
 */
public class Normal extends UserBehaviour {

    public Normal() {
        super("NORMAL", 3);
    }

    @Override
    public int getNumDays(Work work) {
        if (work.getNumberAvailableCopies() == 1)
            return 3;
        else if (work.getNumberAvailableCopies() <= 5)
            return 8;
        
        return 15;
    }

    
}