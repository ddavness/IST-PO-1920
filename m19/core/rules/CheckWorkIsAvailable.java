package m19.core.rules;

import m19.core.Rule;
import m19.core.Library;
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

    public boolean isValid(Request request) {
        return request.getWork().getNumberAvailableCopies() > 0;
    }
    
}