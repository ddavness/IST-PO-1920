package m19.core.rules;
import m19.core.Rule;
import m19.core.Library;
import m19.core.Request;
/**
 * CheckWorkIsLowValue
 */
public class CheckWorkIsLowValue extends Rule {

    static final long serialVersionUID = 20190110170101L;

    public CheckWorkIsLowValue(int id, Library library) {
        super(id, library);
    }

    /**
     * @return a user is suspended iff it is not active
     */

    public boolean isValid(Request request) {
        int MAX_PRICE = 25; // in euros
        return request.getWork().getPrice() <= MAX_PRICE;
    }
    
}