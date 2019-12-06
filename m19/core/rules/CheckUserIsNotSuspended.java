package m19.core.rules;

import m19.core.Rule;
import m19.core.exception.RuleNotSatisfiedException;
import m19.core.Library;
import m19.core.Request;

/**
 * CheckUserIsNotSuspended
 */
public class CheckUserIsNotSuspended extends Rule {

    static final long serialVersionUID = 20190110170103L;

    public CheckUserIsNotSuspended(int id, Library library) {
        super(id, library);
    }

    /**
     * @return a user is suspended if it is not active
     */
    public void check(Request request) throws RuleNotSatisfiedException {
        if (!request.getUser().isActive()) {
            throw new RuleNotSatisfiedException(request.getUser(), request.getWork(), _ruleId);
        }
    }
}