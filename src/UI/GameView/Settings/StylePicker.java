package UI.GameView.Settings;

import UI.Interfaces.NodeViewInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.function.Consumer;

public class StylePicker implements NodeViewInterface {

    private ComboBox<String> myChoices;
    private static final int DEFAULT_COLOR_INDEX = 0;
    private static final String CSS_EXTENSION = ".css";

    public StylePicker(List<String> allChoices, Consumer<String> myStyleChanger) {
        myChoices = new ComboBox<>(FXCollections.observableList(allChoices));
        myChoices.valueProperty().addListener(e -> {
            myStyleChanger.accept(myChoices.getValue() + CSS_EXTENSION);
        });
        myChoices.setValue(allChoices.get(DEFAULT_COLOR_INDEX));
    }

    @Override
    public ComboBox<String> getView() {
        return myChoices;
    }
}
