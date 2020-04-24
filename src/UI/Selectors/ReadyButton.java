package UI.Selectors;

import UI.Interfaces.StylizedNode;
import UI.LanguageBundle;
import Utility.Formatter;
import Utility.HashNoise;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ReadyButton {

    private static final String ACTION_TEXT_KEY = "ActionReadyButton";
    private static final String WAGER_TEXT_KEY = "WagerReadyButton";
    private static final String NEW_GAME_TEXT_KEY = "NewGameReadyButton";

    private Button myButton;
    private SelectorType myType;
    private LanguageBundle myLanguageBundle;

    public ReadyButton(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
        myButton = new Button();
        Formatter.formatSelectorButton(myButton);
        StylizedNode.setStyleID(myButton, this.getClass());
    }

    public void pauseUntilReady(Pane parent, SelectorType type) {
        int nestedLoopKey = HashNoise.addNoise(this);
        String displayedKey = getDisplayKey(type);
        myButton.setText(myLanguageBundle.getBundle().getString(displayedKey));
        myButton.setOnAction(e -> {
            parent.getChildren().remove(myButton);
            Platform.exitNestedEventLoop(nestedLoopKey, null);
        });
        parent.getChildren().add(myButton);
        Platform.enterNestedEventLoop(nestedLoopKey);
    }

    private String getDisplayKey(SelectorType type) {
        if (type.equals(SelectorType.ACTION)) return ACTION_TEXT_KEY;
        else if (type.equals(SelectorType.WAGER)) return WAGER_TEXT_KEY;
        else if (type.equals(SelectorType.NEWGAME)) return NEW_GAME_TEXT_KEY;
        return null;
    }
}
