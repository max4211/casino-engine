package exceptions;

public class EmptyActionListException extends Exception {

    private static final String ERROR_MESSAGE = "There are no options to take. Check your game logic!";
    public EmptyActionListException() {
        super(ERROR_MESSAGE);
    }
}
