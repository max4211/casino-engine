/**
 * This class was shown for a two reasons.
 * First, it is used in the AllFilesDisplay, and to be thorough, its functionality is included here.
 * Second, this shows capability with Functional Interfaces. Not only can I use pre-built ones (such as a Consumer), but I can build by own to create more readable code.
 */
package UI.Interfaces;

/**
 * Functional interface that is used in the AllFilesDisplay to launch a new game.
 * It is assumed that the method passed into a method as a GameLauncher is the method that will start a new round of a game.
 * Takes in no parameters and has no return value. Functionally equivalent to the Runnable interface, but the custom version provides for more readable code.
 * @author Eric Doppelt
 */
@FunctionalInterface
public interface GameLauncher {

    /**
     * Method that is called to start a new game. Takes no parameters and returns no value.
     * An example of how to use this is found with the Launch Button composed into AllFilesDisplay.
     * Simply pass a method via means of a lambda expression to create a GameLauncher and call startNewGame() on it to run.
     */
    void startNewGame();
}
