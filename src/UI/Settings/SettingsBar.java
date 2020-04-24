package UI.Settings;

import UI.Interfaces.StylizedNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.Consumer;

public class SettingsBar implements StylizedNode {

    private HBox myHBox;
    private static final int ICON_SPACING = 40;
    private static final int ICON_INSETS = 10;

    //FIXME: formatting
    public SettingsBar(Consumer<String> acceptCSS, List<String> allCSS, Consumer<String> acceptLanguage, List<String> allLanguages, String helpIcon) {
        myHBox = new HBox();
        StylizedNode.setStyleID(myHBox, this.getClass());
        LanguagePicker myLanguagePicker = new LanguagePicker(allLanguages, acceptLanguage);
        myHBox.getChildren().add(myLanguagePicker.getView());
        HelpButton myHelpButton = new HelpButton(helpIcon);
        myHBox.getChildren().add(myHelpButton.getView());
        StylePicker myStylePicker = new StylePicker(allCSS, acceptCSS);
        myHBox.getChildren().add(myStylePicker.getView());

        myHBox.setAlignment(Pos.CENTER);
        myHBox.setPadding(new Insets(ICON_INSETS));
        myHBox.setSpacing(ICON_SPACING);
    }

    @Override
    public HBox getView() {
        return myHBox;
    }
}
