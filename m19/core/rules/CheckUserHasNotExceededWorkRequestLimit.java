package m19.core.rules;

import m19.core.Rule;
import m19.core.User;
import m19.core.Library;
import m19.core.Request;
/**
 * CheckUserHasNotExceededWorkRequestLimit
 */
public class CheckUserHasNotExceededWorkRequestLimit extends Rule {

    static final long serialVersionUID = 20190110170104L;

    public CheckUserHasNotExceededWorkRequestLimit(int id, Library library) {
        super(id, library);
    }

    /**
     * @return a user is suspended iff it is not active
     */

    public boolean isValid(Request request) {
        User user = request.getUser();
        int maxReqWorks; // Maximum number of works which can be requested
        switch (user.getBehaviour()) {
            case NORMAL:
                maxReqWorks = 3;
                break;
            case CUMPRIDOR:
                maxReqWorks = 5;
                break;
            case FALTOSO:
                maxReqWorks = 1;
                break;
            default:
                maxReqWorks = 0;
                System.err.println("User behaviour not found");
                break;
        }
        return user.getAllRequests().size() + 1 <= maxReqWorks;
        
    }
    
}