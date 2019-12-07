package m19.core.userbehaviour;

import m19.core.Work;

/**
 * Normal
 */
public class Normal extends UserBehaviour {
    static final long serialVersionUID = 201973109733701L;

    public Normal() {
        super("NORMAL", 3);
    }

    @Override
    public int getNumDays(Work work) {
        if (work.getNumberOfCopies() == 1)
            return 3;
        else if (work.getNumberOfCopies() <= 5)
            return 8;
        
        return 15;
    }

}