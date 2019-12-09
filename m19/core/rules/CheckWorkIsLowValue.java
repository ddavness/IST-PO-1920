package m19.core.rules;

import m19.core.Rule;
import m19.core.Library;
import m19.core.Request;

import m19.core.exception.RuleNotSatisfiedException;
/**
 * CheckWorkIsLowValue
 * A work must cost less than 25€.
 */
public class CheckWorkIsLowValue extends Rule {

    static final long serialVersionUID = 20190110170101L;
    
    public CheckWorkIsLowValue(int id, Library library) {
        super(id, library);
    }

    public void check(Request request) throws RuleNotSatisfiedException {
        if (request.getWork().getPrice() > request.getUser().getBehaviour().maximumWorkValueBorrowable()) {
            throw new RuleNotSatisfiedException(request.getUser(), request.getWork(), _ruleId);
        }
    }
}