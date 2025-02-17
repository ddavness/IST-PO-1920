package m19.core.rules;

import m19.core.Rule;
import m19.core.User;
import m19.core.Library;
import m19.core.Request;
import m19.core.exception.*;
/**
 * CheckUserHasNotExceededWorkRequestLimit
 */
public class CheckUserHasNotExceededWorkRequestLimit extends Rule {

    static final long serialVersionUID = 20190110170104L;

    public CheckUserHasNotExceededWorkRequestLimit(int id, Library library) {
        super(id, library);
    }

    /**
     * @return a user is suspended iff it is not active
     */

    public void check(Request request) throws RuleNotSatisfiedException {
        User user = request.getUser();
        int maxReqWorks = user.getBehaviour().maxNumberOfRequestedWorks();

        if (user.getAllRequests().size() + 1 > maxReqWorks) {
            throw new RuleNotSatisfiedException(user, request.getWork(), _ruleId);
        }

    }
    
}