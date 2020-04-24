package actions.factory;

import actions.group.GroupAction;
import actions.individual.IndividualAction;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;

/**
 * Attempting to mimic a "static class" behavior
 */
public class ActionFactory implements ActionFactoryInterface {

    private static final String ACTION_PATH = "actions";
    private final String myActionType;

    public ActionFactory(String type) {
        this.myActionType = type;
    }

    @Override
    public IndividualAction createIndividualAction(String action) {
        try {
            Class clazz = Class.forName(createActionPath(action));
            Constructor ctor = clazz.getConstructor();
            return (IndividualAction) ctor.newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    @Override
    public GroupAction createGroupAction(String action) {
        try {
            Class clazz = Class.forName(createActionPath(action));
            Constructor ctor = clazz.getConstructor();
            return (GroupAction) ctor.newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    protected String createActionPath(String action) {
        return String.format("%s.%s.%s", ACTION_PATH, this.myActionType, action);
    }

}
