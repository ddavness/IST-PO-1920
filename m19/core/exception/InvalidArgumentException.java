package m19.core.exception;

/**
 * Class for representing an error due to input error
 */
public class InvalidArgumentException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;
    private Object _arguments[];

    /**
     * Default constructor
     */
    public InvalidArgumentException(Object... args) {
        _arguments = args;
    }

    /**
     * @param description
     */
    public InvalidArgumentException(String description, Object... args) {
        super(description);
        _arguments = args;
    }

    public Object[] getInfractorArguments() {
        return _arguments;
    }
}