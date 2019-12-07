package m19.core.rules;

import m19.core.Rule;
import m19.core.exception.AllCopiesRequestedException;

import java.util.Collection;

import m19.core.Library;
import m19.core.Notification;
import m19.core.NotificationBroadcaster;
import m19.core.NotificationObserver;
import m19.core.Request;
/**
 * CheckWorkIsAvailable
 */
public class CheckWorkIsAvailable extends Rule {

    static final long serialVersionUID = 20190110170102L;

    public CheckWorkIsAvailable(int id, Library library) {
        super(id, library);
    }

    /**
     * @return if the work the be requested is available
     */

    public void check(Request request) throws AllCopiesRequestedException {
        if (request.getWork().getNumberAvailableCopies() <= 0) {
            NotificationBroadcaster newBroadcaster = _library.spawnBroadcaster();
            throw new AllCopiesRequestedException(request.getUser(), request.getWork(), _ruleId, newBroadcaster);
        }
    }

}