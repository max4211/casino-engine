package exceptions;

/**
 * Thrown when a custom enumerated type cannot be generated
 * @author Max Smith
 */
public class CustomEnumException extends RuntimeException {

    public CustomEnumException(Exception e) {
        super(e.getMessage());
    }
}
