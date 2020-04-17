package UI.Selectors;

import UI.LanguageBundle;
import Utility.Formatter;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SelectorReadyInput {


    private static final String ACTION_TEXT_KEY = "ActionReadyButton";
    private static final String WAGER_TEXT_KEY = "WagerReadyButton";
    private static final String NEW_GAME_TEXT_KEY = "NewGameReadyButton";
    private boolean isPaused;
    private Formatter myFormatter;

    private LanguageBundle myLanguageBundle;
    public SelectorReadyInput(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
        myFormatter = new Formatter();
        isPaused = false;
    }

    public void pauseUntilReady(Pane parent, SelectorType myType) {
        Button pauseButton = new Button();
        Object nestedLoopKey = this;
        pauseButton.setText(myLanguageBundle.getBundle().getString(getKey(myType)));
        myFormatter.formatSelectorButton(pauseButton);
        pauseButton.setOnAction(e -> {
            parent.getChildren().remove(pauseButton);
            Platform.exitNestedEventLoop(nestedLoopKey, null);
            isPaused = false;
        });
        parent.getChildren().add(pauseButton);
        isPaused = true;
        Platform.enterNestedEventLoop(nestedLoopKey);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void renderAgain(Pane parent, SelectorType myType) {
        Platform.exitNestedEventLoop(this, null);
        pauseUntilReady(parent, myType);
    }

    private String getKey(SelectorType type) {
        if (type.equals(SelectorType.ACTION)) return ACTION_TEXT_KEY;
        else if (type.equals(SelectorType.WAGER)) return WAGER_TEXT_KEY;
        else if (type.equals(SelectorType.NEWGAME)) return NEW_GAME_TEXT_KEY;
        return null;
    }
}
