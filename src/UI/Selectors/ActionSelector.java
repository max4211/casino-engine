package UI.Selectors;

import UI.Interfaces.ExceptionCaller;
import UI.LanguageBundle;
import exceptions.EmptyActionListException;
import exceptions.NoUserInputException;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

//TODO: inheritance heirarchy with Wager?
public class ActionSelector {

    private static final int DEFAULT_ACTION_INDEX = 0;
    private static final String ACTION_PROMPT_KEY = "ActionPrompt";
    private static final String NO_ACTION_INPUT = "";
    private LanguageBundle myLanguageBundle;

    public ActionSelector(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
    }

    public String selectAction(List<String> actions, ExceptionCaller caller) {
        if (actions == null || actions.size() == 0) {
            caller.sendException(new EmptyActionListException());
        }

        // TODO: duplication, but can this be fixed?
        Optional<String> myFirstAction = getAction(actions);
        if (!myFirstAction.isEmpty()) return myFirstAction.get();

        Optional<String> mySecondAction = getAction(actions);
        if (!mySecondAction.isEmpty()) return mySecondAction.get();

        caller.sendException(new NoUserInputException());
        return NO_ACTION_INPUT;
    }

    private Optional<String> getAction(List<String> actions) {
        ChoiceDialog<String> actionOptions = new ChoiceDialog(actions.get(DEFAULT_ACTION_INDEX), actions);
        actionOptions.setContentText(myLanguageBundle.getBundle().getString(ACTION_PROMPT_KEY));
        Optional<String> result = actionOptions.showAndWait();
        return result;
    }
}
