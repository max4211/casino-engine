package UI.Settings;

import java.util.List;
import java.util.function.Consumer;

/**
 * subclass of Picker that allows a user to select a new CSS Stylesheet.
 * Returns the user's choice with a ".css" formatted onto it, calling the method to apply the formatted stylesheet to the View it is attached to.
 */
public class StylePicker extends Picker {

    private static final String CSS_EXTENSION = ".css";

    /**
     * Constructor that takes in a list of options for CSS styling and calls super() to the Picker class.
     * @param allChoices is the choices a user can enter for the stylesheets that can be applied to the view.
     * @param myStyleChanger is the consumer that takes in a String and applies it as a formatted CSS stylesheet to the View.
     */
    public StylePicker(List<String> allChoices, Consumer<String> myStyleChanger) {
        super(allChoices, myStyleChanger);
    }

    @Override
    protected String formatInput(String rawSelection) {
        return rawSelection + CSS_EXTENSION;
    }
}
