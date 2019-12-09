package m19.core.userbehaviour;

import java.lang.Double;

import m19.core.Work;
import m19.core.User;
import m19.core.UserBehaviour;

/**
 * Normal
 */
public class Abiding extends UserBehaviour {
    static final long serialVersionUID = 201973109733703L;

    public Abiding() {
        super("CUMPRIDOR", 5, (int)Double.POSITIVE_INFINITY);
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
            currentKarma = -1;
        } else {
            currentKarma++;
        }

        super.setUserKarma(user, currentKarma);

        if (currentKarma < 5) {
            return new Normal();
        }

        return this;
    }

}