package UI.Interfaces;

/**
 * Functional interface that WAS used to replace a Consumer Object to throw errors.  The ExceptionDisplayer.displayException() method was often called by means of a lambda.
 * Method is now OUTDATED, and all exceptions should be thrown by the method and caught in the GameView and NOT in any smaller classes.
 * @author Eric Doppelt
 */
@FunctionalInterface @Deprecated
public interface ExceptionDisplayer {

    /**
     * Method that, when called by means of a lambda expressions, is assumed to call the displayException() method on an exception give to it as a parameter.
     * This is now deprecated since a better design would throw the exception and allow it to move upwards towards a try/catch method.
     * @param ex is the Exception to display.
     */
    void sendException(Exception ex);

}
