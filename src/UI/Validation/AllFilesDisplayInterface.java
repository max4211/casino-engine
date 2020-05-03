package UI.Validation;

import UI.Interfaces.GameCaller;

/**
 * Interface used to encode the AllFilesDisplay by means of Design by Contract.
 * Requires the functionality of updating a file's status and alerting the user that the user can launch the game, once all five files have been validated.
 * This is used in the Validation process to alert the user of his progress to getting 5 valid files.
 * @author Eric Doppelt
 */
public interface AllFilesDisplayInterface {

    /**
     * Method which is called to update the UI's display of a single file status.
     * @param type is the type of file that has changed (such as Deck or Hands).
     * @param ready is the status of the type of file that has changed (such as Valid or Empty).
     */
    void updateStatus(XMLFileType type, FileStatus ready);

    /**
     * Method which is called once the backend is ready to launch the game with 5 valid files.
     * The launch button in the AllFilesDisplay UI is rendered enabled, and its setOnClick() method runs the GameCaller.
     * @param initializer is a GameCaller Functional Interface which allows the AllFilesDisplay to communicate with the Controller and alert it to create a game by means of a lambda.
     */
    void enableGameButton(GameCaller initializer);

}
