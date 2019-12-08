package m19.core.rules;

import m19.core.Rule;
import m19.core.Library;
import m19.core.Request;
import m19.core.userbehaviour.Abiding;

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
        int MAX_PRICE = 25; // in euros
        if (request.getWork().getPrice() > MAX_PRICE && request.getUser().getBehaviour().toString() != new Abiding().toString()) {
            throw new RuleNotSatisfiedException(request.getUser(), request.getWork(), _ruleId);
        }
    }
}