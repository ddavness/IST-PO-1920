package m19.core.userbehaviour;

import m19.core.Work;
import m19.core.User;
import m19.core.UserBehaviour;

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

    @Override
    public UserBehaviour updateKarma(User user, boolean lateReturn) {
        int currentKarma = user.getKarma();
        if (lateReturn) {
            currentKarma = Math.max(0, currentKarma) - 1;
        } else {
            currentKarma = Math.min(0, currentKarma) + 1;
        }

        super.setUserKarma(user, currentKarma);

        if (currentKarma <= -3) {
            return new Faulty();
        } else if (currentKarma >= 5) {
            return new Abiding();
        }
        return new Normal();
    }

}