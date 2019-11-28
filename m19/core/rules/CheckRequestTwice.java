package m19.core.rules;

import m19.core.Rule;
import m19.core.exception.RuleNotSatisfiedException;
import m19.core.Library;
import m19.core.Request;

/**
 * CheckRequestTwice
 * A request must not be repeated
 */
 public class CheckRequestTwice extends Rule {

    static final long serialVersionUId = 20190110170105L;

    public CheckRequestTwice(int id, Library library) {
        super(id, library);
    }

    @Override
    public void check(Request request) throws RuleNotSatisfiedException {
        for (Request _request: request.getUser().getAllRequests()) {
            if (_request.equals(request))
                throw new RuleNotSatisfiedException(request.getUser(), request.getWork(), _ruleId);
        }
    }

}