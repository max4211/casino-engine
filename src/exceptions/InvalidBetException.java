package exceptions;

public class InvalidBetException extends Exception {

    private static final String EXCEPTION_MESSAGE = "You did not enter a valid bet for the range!";

    public InvalidBetException() {
        super(EXCEPTION_MESSAGE);
    }
}
