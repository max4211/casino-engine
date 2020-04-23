package UI.Settings;

import java.util.List;
import java.util.function.Consumer;

public class LanguagePicker extends Picker {

        public LanguagePicker(List<String> allChoices, Consumer<String> myStyleChanger) {
            super(allChoices, myStyleChanger);
        }

    @Override
    protected String formatInput(String selection) {
        return selection;
    }
}
