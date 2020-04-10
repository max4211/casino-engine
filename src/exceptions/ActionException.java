package exceptions;

public class ActionException extends RuntimeException {

    public ActionException(Exception e) {
        super(e.getMessage());
    }

    public ActionException() {
        super();
    }

}
