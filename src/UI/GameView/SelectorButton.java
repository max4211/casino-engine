package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class SelectorButton implements NodeViewInterface {

    private Button myButton;
    private static final String RESOURCE_LANGUAGE = "English";
    private static final ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);
    private static final String SELECTOR_BUTTON_KEY = "SelectorButton";


    public SelectorButton(String selectionType) {
        myButton = new Button();
        myButton.setText(myResources.getString(myResources.getString(SELECTOR_BUTTON_KEY) + myResources.getString(selectionType)));
    }

    @Override
    public Button getView() {
        return myButton;
    }
}
