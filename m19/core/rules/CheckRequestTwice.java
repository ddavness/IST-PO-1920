package m19.core.rules;

import m19.core.Rule;
import m19.core.Library;
import m19.core.Request;

/**
 * CheckRequestTwice
 */
 public class CheckRequestTwice extends Rule {

    static final long serialVersionUID = 20190110170105L;

    public CheckRequestTwice(int id, Library library) {
        super(id, library);
    }

    @Override
    public boolean isValid(Request request) {
        for (Request _request: _library.getAllRequests()) {
            if (_request.equals(request))
                return false;
        }

        return true;
    }

}