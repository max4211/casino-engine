package UI.Settings;

import UI.Interfaces.NodeViewInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.Consumer;

public class SettingsBar implements NodeViewInterface {

    private HBox myHBox;
    private static final String CSS_ID = "settings-bar";
    private static final int ICON_SPACING = 40;
    private static final int ICON_INSETS = 10;

    //FIXME: formatting
    public SettingsBar(Consumer<String> acceptCSS, List<String> allCSS, Consumer<String> acceptLanguage, List<String> allLanguages, String fileBuilderIcon, String helpIcon) {
        myHBox = new HBox();
        myHBox.setId(CSS_ID);

        LanguagePicker myLanguagePicker = new LanguagePicker(allLanguages, acceptLanguage);
        myHBox.getChildren().add(myLanguagePicker.getView());
        FileBuilderButton myFileBuilderButton = new FileBuilderButton(fileBuilderIcon);
        myHBox.getChildren().add(myFileBuilderButton.getView());
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
