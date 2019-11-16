package m19.core;

/**
 * Rule
 */
public abstract class Rule {
    private final int _id;
    public Rule(int id) {
        _id = id;
    }
    /**
     * 
     * @param user
     * @param work
     * @return true iff the work can be requested by the user
     */
    public abstract boolean isValid(User user, Work work);

}