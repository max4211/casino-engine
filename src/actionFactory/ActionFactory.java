package actionFactory;

import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Attempting to mimic a "static class" behavior
 */
public class ActionFactory implements ActionFactoryInterface {

    private List<String> myPossibleActions;
    private static final String ACTION_FACTORY = "actionFactory";

    // TODO - remove possible actioons (currently unused)
    public ActionFactory(List<String> possibleActions) {
        this.myPossibleActions = possibleActions;
    }


    @Override
    public Action createAction(String action) {
        try {
            Class clazz = Class.forName(createActionPath(action));
            Constructor ctor = clazz.getConstructor();
            return (Action) ctor.newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private String createActionPath(String action) {
        return String.format("%s.%s", ACTION_FACTORY, action);
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
