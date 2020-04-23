package exceptions;

public class CustomEnumException extends RuntimeException {

    public CustomEnumException(Exception e) {
        super(e.getMessage());
    }
}
