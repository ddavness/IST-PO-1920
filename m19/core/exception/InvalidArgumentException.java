package m19.core.exception;

/**
 * Class for representing an error due to input error
 */
public class InvalidArgumentException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;
    private Object _argument;

    /**
     * Default constructor
     */
    public InvalidArgumentException(int argument) {
        _argument = argument;
    }

    /**
     * @param description
     */
    public InvalidArgumentException(int argument, String description) {
        super(description);
        _argument = argument;
    }

    /**
     * @param cause
     */
    public InvalidArgumentException(Exception cause) {
        super(cause);
    }

    public Object getInfractorArgument() {
        return _argument;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ", got " + _argument;
    }
}