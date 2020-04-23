package UI.Settings;

import UI.Interfaces.NodeViewInterface;
import Utility.Formatter;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.Consumer;

public class SettingsBar implements NodeViewInterface {

    private HBox myHBox;
    private Formatter myFormatter;

    public SettingsBar(Consumer<String> acceptCSS, List<String> allCSS, Consumer<String> acceptLanguage, List<String> allLanguages, String fileBuilderIcon, String helpIcon) {
        myHBox = new HBox();
        myFormatter = new Formatter();
        LanguagePicker myLanguagePicker = new LanguagePicker(allLanguages, acceptLanguage);
        myHBox.getChildren().add(myLanguagePicker.getView());
        StylePicker myStylePicker = new StylePicker(allCSS, acceptCSS);
        myHBox.getChildren().add(myStylePicker.getView());
        FileBuilderButton myFileBuilderButton = new FileBuilderButton(fileBuilderIcon);
        myHBox.getChildren().add(myFileBuilderButton.getView());
        HelpButton myHelpButton = new HelpButton(helpIcon);
        myHBox.getChildren().add(myHelpButton.getView());
        myFormatter.formatUnfixedCenter(myHBox);
    }

    @Override
    public HBox getView() {
        return myHBox;
    }
}
