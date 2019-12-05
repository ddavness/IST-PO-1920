package m19.core.userbehaviour;

import m19.core.Work;

/**
 * Normal
 */
public class Faulty extends UserBehaviour {

    Faulty() {
        super("FALTOSO", 1);
    }

    @Override
    public int getNumDays(Work work) {
        return 2;
    }

    
}