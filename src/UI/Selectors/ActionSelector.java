package UI.Selectors;

import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ActionSelector {

    private static final int DEFAULT_ACTION_INDEX = 0;
    private static final String ACTION_PROMPT_KEY = "ActionPrompt";

    private static final String INITIAL_RESOURCE_LANGUAGE = "English";
    private static ResourceBundle myResources = ResourceBundle.getBundle(INITIAL_RESOURCE_LANGUAGE);

    public static String selectAction(List<String> actions) {
        if (actions == null || actions.size() == 0) {
            System.out.println("EXCEPTION NEEDS TO BE HANDLED");
            return null;
        }

        ChoiceDialog<String> actionOptions = new ChoiceDialog(actions.get(DEFAULT_ACTION_INDEX), actions);
        actionOptions.setContentText(myResources.getString(ACTION_PROMPT_KEY));
        Optional<String> result = actionOptions.showAndWait();
        // TODO: this makes cancelling rerun, find a better way in Sprint 2
        if (result.isEmpty()) {
            System.out.println("ENTER A VALUE");
            return selectAction(actions);
        }
        return result.get();
    }
}
