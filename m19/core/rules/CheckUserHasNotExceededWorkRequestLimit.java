package m19.core.rules;
import m19.core.Rule;
import m19.core.User;
import m19.core.Work;
import m19.core.Library;
import m19.core.Request;
/**
 * CheckUserHasNotExceededWorkRequestLimit
 */
public class CheckUserHasNotExceededWorkRequestLimit extends Rule {

    public CheckUserHasNotExceededWorkRequestLimit(int id, Library library) {
        super(id, library);
    }

    /**
     * @return a user is suspended iff it is not active
     */

    public boolean isValid(Request request) {
        User user = request.getUser();
        switch (user.get) {
            case value:
                
                break;
        
            default:
                break;
        }
        
    }
    
}