package UI.GameView.Settings;

import UI.Interfaces.NodeViewInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.function.Consumer;

public abstract class Picker implements NodeViewInterface {

    protected ComboBox<String> myChoices;
    private static final int DEFAULT_INDEX = 0;

    public Picker(List<String> allChoices, Consumer<String> myStyleChanger) {
        myChoices = new ComboBox<>(FXCollections.observableList(allChoices));
        myChoices.valueProperty().addListener(e -> {
            myStyleChanger.accept(formatInput(myChoices.getValue()));
        });
        myChoices.setValue(allChoices.get(DEFAULT_INDEX));
    }

    @Override
    public ComboBox<String> getView() {
        return myChoices;
    }

    protected abstract String formatInput(String rawSelection);
}
