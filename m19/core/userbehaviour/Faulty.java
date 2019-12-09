package m19.core.userbehaviour;

import m19.core.Work;
import m19.core.User;
import m19.core.UserBehaviour;

/**
 * Normal
 */
public class Faulty extends UserBehaviour {
    static final long serialVersionUID = 201973109733702L;

    public Faulty() {
        super("FALTOSO", 1, 25);
    }

    @Override
    public int getNumDays(Work work) {
        return 2;
    }

    @Override
    public UserBehaviour updateKarma(User user, boolean lateReturn) {
        int currentKarma = user.getKarma();
        if (lateReturn) {
            currentKarma--;
        } else {
            currentKarma = Math.max(0, currentKarma) + 1;
        }

        super.setUserKarma(user, currentKarma);

        if (currentKarma >= 3) {
            return new Normal();
        }
        return this;
    }

}