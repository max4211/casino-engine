package UI.Selectors;

import Utility.Formatter;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class SelectorReadyInput {

    private static final String RESOURCE_LANGUAGE = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);
    private static final String ACTION_TEXT_KEY = "ActionReadyButton";
    private static final String WAGER_TEXT_KEY = "WagerReadyButton";
    private static final Formatter myFormatter = new Formatter();

    private static final String NESTED_LOOP_KEY = "twenty-one";

    public static void pauseUntilReady(Pane parent, SelectorType myType) {
        Button pauseButton = new Button();
        pauseButton.setText(myResources.getString(getKey(myType)));
        myFormatter.formatSelectorButton(pauseButton);
        pauseButton.setOnAction(e -> {
            parent.getChildren().remove(pauseButton);
            Platform.exitNestedEventLoop(NESTED_LOOP_KEY, null);
        });
        parent.getChildren().add(pauseButton);
        Platform.enterNestedEventLoop(NESTED_LOOP_KEY);
    }

    private static String getKey(SelectorType type) {
        if (type.equals(SelectorType.ACTION)) return ACTION_TEXT_KEY;
        else if (type.equals(SelectorType.WAGER)) return WAGER_TEXT_KEY;
        return null;
    }
}
