package m19.core.rules;
import m19.core.Rule;
import m19.core.User;
import m19.core.Work;
import m19.core.Library;
import m19.core.Request;
/**
 * CheckUserIsSuspended
 */
public class CheckUserIsSuspended extends Rule {

    public CheckUserIsSuspended(int id, Library library) {
        super(id, library);
    }

    /**
     * @return a user is suspended iff it is not active
     */

    public boolean isValid(Request request) {
        return request.getUser().isActive();
    }

}