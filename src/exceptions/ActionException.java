package exceptions;

public class ActionException extends RuntimeException {

    //TODO: Max, is this right?

    private static final String EXCEPTION_MESSAGE = "ACTION EXCEPTION";
    public ActionException(Exception e) {
        super(EXCEPTION_MESSAGE);
    }

    public ActionException() {
        super(EXCEPTION_MESSAGE);
    }

}
