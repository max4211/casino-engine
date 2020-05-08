package exceptions;

/**
 * Thrown at runtime when reflection cannot be completed (in the backend)
 * @author Max Smith
 */
public class ReflectionException extends RuntimeException {

    public ReflectionException(Exception e) {
        super(e.getMessage());
    }

    public ReflectionException() {
        super();
    }
}
