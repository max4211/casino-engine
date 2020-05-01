package exceptions;

import actions.group.GroupAction;
import actions.individual.IndividualAction;

/**
 * Exception thrown when an action cannot be created, displayed in view
 * @author Max Smith
 */
public class ActionException extends RuntimeException {

    private String myMessage;

    public ActionException(IndividualAction action) {
        super();
        setMessage(action);
    }

    public ActionException(GroupAction action) {
        super();
        setMessage(action);
    }

    public ActionException(Exception e, IndividualAction action) {
        super(e.getCause());
        setMessage(action);
    }

    public ActionException(Exception e, GroupAction action) {
        super(e.getCause());
        setMessage(action);
    }

    private void setMessage(Object action) {
        String simpleName = action.getClass().getSimpleName();
        this.myMessage = String.format("Sorry, could not create action %s, please try again", simpleName);
    }

    public String getMessage() {
        return this.myMessage;
    }


}
