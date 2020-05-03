package UI.Settings;

import java.util.List;
import java.util.function.Consumer;

/**
 * Subclass of Picker that represents all the language options a user has.
 * Extends picker and simply returns the user's choice in the most basic implementation.
 * @author Eric Doppelt
 */
public class LanguagePicker extends Picker {

    /**
     * Constructor that simply calls super() for the original abstract Picker.
     * @param allChoices is the language options a user has
     * @param myStyleChanger is the method that updates the language in the view that the picker is attached to.
     */
    public LanguagePicker(List<String> allChoices, Consumer<String> myStyleChanger) {
        super(allChoices, myStyleChanger);
    }

    @Override
    protected String formatInput(String selection) {
        return selection;
    }
}
