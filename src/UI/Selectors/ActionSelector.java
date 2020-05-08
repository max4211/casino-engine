package UI.Selectors;

import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import exceptions.EmptyActionListException;
import exceptions.NoUserInputException;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

/**
 * Class that encodes functionality in a Dialogue used to select Actions during a round of a card game.
 * Handles languages without the need of the LanguageResponder interface, since Dialogues fetch their text when called, so they are always up-to-date.
 * To use this, simply create an ActionSelector and call the selectAction() method.
 * @author Eric Doppelt
 */
public class ActionSelector {

    private LanguageBundle myLanguageBundle;

    private static final int DEFAULT_ACTION_INDEX = 0;
    private static final String ACTION_PROMPT_KEY = "ActionPrompt";

    /**
     * Basic constructor that only takes in a LanguageBundle later used to display text in a variety of languages.
     * @param languageBundle is the LanguageBundle that is accessed in subsequent calls of selectAction().
     */
    public ActionSelector(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
    }

    /**
     * Method that encodes the majority of the functionality in this class, prompting the user for input via a Dialogue.
     * A user can only select an action from the list of Strings given as a parameter.
     * The only case in which an exception is NOT thrown is when a user inputs a valid action from his or her choices.
     * @param actions is the list of actions (Strings) that the user can pick from
     * @return a String that is the users selection, guaranteed to exist (not be null) if an error is not thrown.
     * @throws EmptyActionListException is thrown if there are no actions to choose from (Bad input from backend).
     * @throws NoUserInputException is thrown if the user does not select an action and instead hits cancel (Bad input from user).
     */
    public String selectAction(List<String> actions) throws EmptyActionListException, NoUserInputException {
        if (actions == null || actions.size() == 0) {
            throw new EmptyActionListException();
        }
        Optional<String> mySelection = getAction(actions);
        if (mySelection.isEmpty()) throw new NoUserInputException();
        else return mySelection.get();
    }

    private Optional<String> getAction(List<String> actions) {
        ChoiceDialog<String> actionOptions = new ChoiceDialog(actions.get(DEFAULT_ACTION_INDEX), actions);
        StylizedNode.setStyleID(actionOptions.getDialogPane(), this.getClass());
        String inputPrompt = myLanguageBundle.getBundle().getString(ACTION_PROMPT_KEY);
        actionOptions.setContentText(inputPrompt);
        return actionOptions.showAndWait();
    }
}
