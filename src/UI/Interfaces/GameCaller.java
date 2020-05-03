package UI.Interfaces;

/**
 * Functional interface that is used in the AllFilesDisplay to launch a new game.
 * It is assumed that the method passed into a method as a GameCaller is the method that will start a new round of a game.
 * Takes in no parameters and has no return value. Functionally equivalent to the Method Runner, but has more readable code for its specific purpose.
 * @author Eric Doppelt
 */
@FunctionalInterface
public interface GameCaller {

    /**
     * Method that is called to start a new game. Takes no parameters and returns no value.
     * An example of how to use this is found the enable button in AllFilesDisplay.
     * Simply pass a method via means of a lambda expression to create a GameCaller and call startNewGame() on it to run.
     */
    void startNewGame();
}
