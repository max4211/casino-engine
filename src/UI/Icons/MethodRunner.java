package UI.Icons;

/**
 * Custom functional interface that improves the readability of the ClickableIcon subclass.
 * Used with lambda expressions to pass in a method that is executed on the click of the Icon.
 * Method takes in no parameters and returns nothing.
 * @author Eric Doppelt
 */
@FunctionalInterface
public interface MethodRunner {

    /**
     * Method that is executed on the click of an icon. Note that this differs from standard functional interfaces because it does not
     * consume any parameters or supply any return value.
     */
    void runMethod();

}
