package UI.LobbyView;

import UI.ExceptionHandling.ExceptionDisplayer;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.Settings.SettingsBar;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LobbyView implements NodeViewInterface {

    VBox myVBox;
    FlowPane myFlowPane;

    private static final String PATH_TO_ICONS = "resources/iconImages/lobbyViewIcons/";
    private static final String PATH_TO_STYLESHEETS = "styleSheets/lobby/";

    private static final String INFO_TAG = "Info";
    private static final String ERROR_TAG = "Error";

    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";

    private static final String CSS_ID = "lobby-view";
    private static final int DEFAULT_CSS_INDEX = 0;
    private static final int DEFAULT_LANGUAGE_INDEX = 0;

    private static final int VBOX_SPACING = 5;

    private LanguageBundle myLanguageBundle;
    private ExceptionDisplayer myExceptionDisplayer;

    public LobbyView(List<String> styleSheets, List<String> languages, String iconProperties, String errorCSS, List<Map<String, String>> generalInfo, List<List<File>> files) {

        myVBox = new VBox();
        myVBox.setId(CSS_ID);
        myVBox.setSpacing(VBOX_SPACING);
        updateCSS(styleSheets.get(DEFAULT_CSS_INDEX));

        ResourceBundle myIconResources = ResourceBundle.getBundle(iconProperties);
        myLanguageBundle = new LanguageBundle(languages.get(DEFAULT_LANGUAGE_INDEX));
        System.out.println(myIconResources.getString(ERROR_TAG));
        myExceptionDisplayer = new ExceptionDisplayer(myIconResources.getString(ERROR_TAG), errorCSS, myLanguageBundle);
        SettingsBar addedSettings = new SettingsBar(e -> updateCSS(e), styleSheets, e -> updateLanguage(e), languages, myIconResources.getString(INFO_TAG));
        myVBox.getChildren().add(addedSettings.getView());

        myFlowPane = new FlowPane();
        myFlowPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < generalInfo.size(); i++) {
            Map<String, String> tempGeneralInfo = generalInfo.get(i);
            List<File> tempFiles = files.get(i);

            GameIcon tempIcon = new GameIcon(
                    tempGeneralInfo.get(ICON_TAG),
                    tempGeneralInfo.get(NAME_TAG),
                    tempFiles,
                    (ex) -> this.myExceptionDisplayer.displayException(ex));

            myFlowPane.getChildren().add(tempIcon.getView());
        }

        myVBox.getChildren().add(myFlowPane);
    }

    private void updateCSS(String newStyleSheet) {
        myVBox.getStylesheets().clear();
        myVBox.getStylesheets().add(PATH_TO_STYLESHEETS.concat(newStyleSheet));
    }

    private void updateLanguage(String newLanguage) {
        myLanguageBundle.setLanguage(newLanguage);
    }

    @Override
    public VBox getView() {
        return myVBox;
    }
 }
