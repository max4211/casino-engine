package exceptions;

public class IncompatibleBetRestrictionException extends Exception {

    private static final String EXCEPTION_MESSAGE = "The maximum bet is less than the minimum!";

    public IncompatibleBetRestrictionException() {
        super(EXCEPTION_MESSAGE);
    }
}
