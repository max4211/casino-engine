package UI.Settings;

import UI.Interfaces.StylizedNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.Consumer;

/**
 * Method that represents the entire SettingsBar, consisting of the composition of a StylePicker, LanguagePicker, and HelpButton.
 * Always assumed to be displayed at the top of a scene that is is placed on.
 * Implements the StylizedNode interface, returning an HBox with a CSS ID of SettingsBar on the method call getView().
 * @author Eric Doppelt
 */
public class SettingsBar implements StylizedNode {

    private HBox myHBox;
    private static final int ICON_SPACING = 40;
    private static final int ICON_INSETS = 10;

    /**
     * Basic constructor that initializes the SettingBar by means of passing parameters to Pickers and a Help Button and adding them to an HBox.
     * Calls the Formatter to format and style the Settings Bar, giving it a CSS ID of SettingsBar.
     * @param acceptCSS is a consumer containing the method that applies a CSS stylesheet to the view holding the settings bar.
     * @param allCSS is a list of Strings representing all CSS files a user can select.
     * @param acceptLanguage is a consumer containing the method that applies a new language resource bundle to the view signifying a language change.
     * @param allLanguages is a list of Strings representing all language files a user can select.
     * @param helpIcon is a string giving the path to the image shown in the help icon.
     * @param webURL is a string giving the URL that the HelpIcon links to.
     */
    public SettingsBar(Consumer<String> acceptCSS, List<String> allCSS, Consumer<String> acceptLanguage, List<String> allLanguages,
                       String helpIcon, String webURL) {
        myHBox = new HBox();
        StylizedNode.setStyleID(myHBox, this.getClass());
        LanguagePicker myLanguagePicker = new LanguagePicker(allLanguages, acceptLanguage);
        myHBox.getChildren().add(myLanguagePicker.getView());
        HelpButton myHelpButton = new HelpButton(helpIcon, webURL);
        myHBox.getChildren().add(myHelpButton.getView());
        StylePicker myStylePicker = new StylePicker(allCSS, acceptCSS);
        myHBox.getChildren().add(myStylePicker.getView());

        myHBox.setAlignment(Pos.CENTER);
        myHBox.setPadding(new Insets(ICON_INSETS));
        myHBox.setSpacing(ICON_SPACING);
    }

    /**
     * Basic method that an HBox containing the entirety of the SettingsBar in a Node.
     * @return HBox that contains a StylePicker, HelpIcon, and LanguagePicker ready to be used.
     */
    @Override
    public HBox getView() {
        return myHBox;
    }
}
