package exceptions;

public class NoUserInputException extends Exception {

    private static String EXCEPTION_MESSAGE = "You did not enter anything! Please do when ready.";

    public NoUserInputException() {
        super(EXCEPTION_MESSAGE);
    }
}
