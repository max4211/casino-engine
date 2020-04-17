package UI.Selectors;

import UI.LanguageBundle;
import Utility.Formatter;
import Utility.HashNoise;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SelectorReadyInput {


    private static final String ACTION_TEXT_KEY = "ActionReadyButton";
    private static final String WAGER_TEXT_KEY = "WagerReadyButton";
    private static final String NEW_GAME_TEXT_KEY = "NewGameReadyButton";

    private Button myButton;
    private Pane myParent;
    private SelectorType myType;

    private boolean isPaused;
    private Formatter myFormatter;

    private LanguageBundle myLanguageBundle;
    public SelectorReadyInput(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
        myFormatter = new Formatter();
        isPaused = false;
    }

    public void pauseUntilReady(Pane parent, SelectorType type) {
        myButton = new Button();
        myParent = parent;
        myType = type;
        int nestedLoopKey = HashNoise.addNoise(this);
        myButton.setText(myLanguageBundle.getBundle().getString(getKey(myType)));
        myFormatter.formatSelectorButton(myButton);
        myButton.setOnAction(e -> {
            parent.getChildren().remove(myButton);
            Platform.exitNestedEventLoop(nestedLoopKey, null);
            isPaused = false;
        });
        parent.getChildren().add(myButton);
        isPaused = true;
        Platform.enterNestedEventLoop(nestedLoopKey);
    }

    public void updateLanguage() {
        //if (isPaused) myButton.fire();
        //pauseUntilReady(myParent, myType);
    }

    private String getKey(SelectorType type) {
        if (type.equals(SelectorType.ACTION)) return ACTION_TEXT_KEY;
        else if (type.equals(SelectorType.WAGER)) return WAGER_TEXT_KEY;
        else if (type.equals(SelectorType.NEWGAME)) return NEW_GAME_TEXT_KEY;
        return null;
    }
}
