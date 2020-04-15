package UI.Selectors;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class SelectorButton implements NodeViewInterface {

    private Button myButton;
    private static final String RESOURCE_LANGUAGE = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);
    private static final String ACTION_TEXT_KEY = "ActionReadyButton";
    private static final String WAGER_TEXT_KEY = "WagerReadyButton";
    private Formatter myFormatter;

    public SelectorButton(Pane parent, SelectorType myType) {
        myButton = new Button();
        myButton.setText(myResources.getString(getKey(myType)));
        myFormatter = new Formatter();
        myFormatter.formatSelectorButton(myButton);
        myButton.setOnAction(e -> {
            parent.getChildren().remove(myButton);
            Platform.exitNestedEventLoop(this, null);
        });
    }

    public void startPause() {
        Platform.enterNestedEventLoop(this);
    }

    @Override
    public Button getView() {
        return myButton;
    }

    private String getKey(SelectorType type) {
        if (type.equals(SelectorType.ACTION)) return ACTION_TEXT_KEY;
        else if (type.equals(SelectorType.WAGER)) return WAGER_TEXT_KEY;
        return null;
    }
}
