package m19.core.rules;

import m19.core.Rule;
import m19.core.Library;
import m19.core.Request;
import m19.core.Category;

import m19.core.exception.RuleNotSatisfiedException;

/**
 * CheckWorkIsNotReference
 * A work must not of the reference category.
 */
public class CheckWorkIsNotReference extends Rule {

    static final long serialVersionUID = 20190110170101L;
    
    public CheckWorkIsNotReference(int id, Library library) {
        super(id, library);
    }

    public void check(Request request) throws RuleNotSatisfiedException {
        if (request.getWork().getCategory() == Category.REFERENCE) {
            throw new RuleNotSatisfiedException(request.getUser(), request.getWork(), _ruleId);
        }
    }
}