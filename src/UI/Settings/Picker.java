package UI.Settings;

import UI.Interfaces.StylizedNode;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.function.Consumer;

/**
 * Abstract superclass used in the inheritance hierarchy of Pickers used in the game.
 * Encodes a combo box that calls a method given in construction via a consumer once anything is selected.
 * Subclasses are LanguagePicker and SettingsBar.
 * Implements the StylizedNode interface, which returns the ComboBox with a CSS ID of Picker.
 * @author Eric Doppelt
 */
public abstract class Picker implements StylizedNode {

    protected ComboBox<String> myChoices;
    private static final int DEFAULT_INDEX = 0;

    /**
     * Constructor that creates a ComboBox with the specified options given as a list allChoices.
     * Upon a selection, the myStyleChanger method is called.
     * @param allChoices is a list of strings which represent all the choices a user can make in this picker.
     * @param myStyleChanger is a consumer which accepts a string selection whenever a user selects a choice.
     */
    public Picker(List<String> allChoices, Consumer<String> myStyleChanger) {
        myChoices = new ComboBox<>(FXCollections.observableList(allChoices));
        StylizedNode.setStyleID(myChoices, this.getClass());
        myChoices.valueProperty().addListener(e -> {
            myStyleChanger.accept(formatInput(myChoices.getValue()));
        });
        myChoices.setValue(allChoices.get(DEFAULT_INDEX));
    }

    /**
     * Method that implements the StylizedNode interface, returning a ComboBox on call.
     * @return a ComboBox that contains all the options specified in the picker and a listener for new input.
     */
    @Override
    public ComboBox<String> getView() {
        return myChoices;
    }

    protected abstract String formatInput(String rawSelection);
}
