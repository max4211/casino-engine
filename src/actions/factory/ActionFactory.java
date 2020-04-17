package actions.factory;

import actions.individual.IndividualAction;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;

/**
 * Attempting to mimic a "static class" behavior
 */
public class ActionFactory implements ActionFactoryInterface {

    private static final String ACTION_PATH = "actions";
    private String ACTION_TYPE;

    public ActionFactory() {

    }

    public ActionFactory(String type) {
        this.ACTION_TYPE = type;
    }


    @Override
    public IndividualAction createAction(String action) {
        try {
            Class clazz = Class.forName(createActionPath(action));
            Constructor ctor = clazz.getConstructor();
            return (IndividualAction) ctor.newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private String createActionPath(String action) {
        return String.format("%s.%s.%s", ACTION_PATH, ACTION_TYPE, action);
    }

    /** Max's team code for reflection example within their execution (shows how to invoke a method)
     *
     *     private List<String> executeCommand(Command command) {
     *         try {
     *             Class superclazz = command.getClass().getSuperclass();
     *             String name = EXECUTE + superclazz.getSimpleName();
     *             Method method = this.getClass().getDeclaredMethod(name, superclazz); //Command.class
     *             Object o = method.invoke(this, command);
     *             return (List<String>) o;
     *         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | NullPointerException e) {
     *             throw new ReflectionException("Unable to apply Reflection in parser");
     *         }
     *     }
     */

}
