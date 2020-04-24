package UI.Selectors;

import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import exceptions.EmptyActionListException;
import exceptions.NoUserInputException;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

public class ActionSelector {

    private LanguageBundle myLanguageBundle;

    private static final int DEFAULT_ACTION_INDEX = 0;
    private static final String ACTION_PROMPT_KEY = "ActionPrompt";


    public ActionSelector(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
    }

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
