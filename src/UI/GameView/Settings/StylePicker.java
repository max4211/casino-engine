package UI.GameView.Settings;

import java.util.List;
import java.util.function.Consumer;

public class StylePicker extends Picker {

    private static final String CSS_EXTENSION = ".css";

    public StylePicker(List<String> allChoices, Consumer<String> myStyleChanger) {
        super(allChoices, myStyleChanger);
    }

    @Override
    protected String formatInput(String rawSelection) {
        return rawSelection + CSS_EXTENSION;
    }
}
