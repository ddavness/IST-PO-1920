package m19.core.rules;
import m19.core.Rule;
import m19.core.User;
import m19.core.Work;
import m19.core.Library;
import m19.core.Request;
/**
 * CheckWorkIsAvailable
 */
public class CheckWorkIsAvailable extends Rule {

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