package actions.factory;

import actions.group.GroupAction;
import actions.individual.IndividualAction;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;

/**
 * The action factory us used to create abstract action objects, which all exhibit the
 * command design pattern. Actions are selected through dialogs in the view, then generated
 * using this factory by the controller.
 */
public class ActionFactory implements ActionFactoryInterface {

    private static final String ACTION_PATH = "actions";
    private final String myActionType;

    /**
     * The public constructor for the ActionFactory. When constructed, the factory
     * will make actions of a specific type which is conducive to the appropriate game
     * @param type is the type of ActionFactory (expecting Group or Individual)
     */
    public ActionFactory(String type) {
        this.myActionType = type;
    }

    /**
     * Creates an action in an Adversary game
     * @param action is the string name denoting the appropriate action to create
     * @return the appropriate action using reflection as desired by the controller
     */
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

    /**
     * Creates an action in a Group game
     * @param action is the string name denoting the appropriate action to create
     * @return the appropriate action using reflection as desired by the controller
     */
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
