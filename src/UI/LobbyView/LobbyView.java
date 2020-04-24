package UI.LobbyView;

import UI.ExceptionDisplay.ExceptionDisplayer;
import UI.Interfaces.StylizedNode;
import UI.Settings.SettingsBar;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LobbyView implements StylizedNode {

    VBox myVBox;
    FlowPane myFlowPane;

    private static final String PATH_TO_STYLESHEETS = "styleSheets/lobby/";
    private static final String PATH_TO_ICON_BUNDLE = "iconBundles/lobbyBundles/";
    private static final String PATH_TO_ICON_IMAGE = "iconImages/lobbyIcons/";

    private static final String INFO_TAG = "Info";
    private static final String ERROR_TAG = "Error";

    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";

    private static final int DEFAULT_CSS_INDEX = 0;
    private static final int DEFAULT_LANGUAGE_INDEX = 0;


    private LanguageBundle myLanguageBundle;
    private List<GameStarter> myGameStarters;
    private ExceptionDisplayer myExceptionDisplayer;

    public LobbyView(List<String> styleSheets, List<String> languages, String iconProperties, String errorCSS,
                     List<Map<String, String>> generalInfo, List<List<File>> files,
                     String filesDisplayIcon, String filesDisplayStatus) {

        myVBox = new VBox();
        StylizedNode.setStyleID(myVBox, this.getClass());
        Formatter.formatLobbyView(myVBox);

        myGameStarters = new ArrayList<>();
        updateCSS(styleSheets.get(DEFAULT_CSS_INDEX));
        ResourceBundle myIconResources = ResourceBundle.getBundle(PATH_TO_ICON_BUNDLE.concat(iconProperties));
        myLanguageBundle = new LanguageBundle(languages.get(DEFAULT_LANGUAGE_INDEX));
        myExceptionDisplayer = new ExceptionDisplayer(myIconResources.getString(ERROR_TAG), errorCSS, myLanguageBundle);
        SettingsBar addedSettings = new SettingsBar(e -> updateCSS(e), styleSheets, e -> updateLanguage(e), languages, PATH_TO_ICON_IMAGE.concat(myIconResources.getString(INFO_TAG)));
        myVBox.getChildren().add(addedSettings.getView());

        myFlowPane = new FlowPane();
        Formatter.formatGameStarterFlowPane(myFlowPane);
        
        for (int i = 0; i < generalInfo.size(); i++) {
            Map<String, String> tempGeneralInfo = generalInfo.get(i);
            List<File> tempFiles = files.get(i);
            tempGeneralInfo.get(ICON_TAG);
            GameStarter tempGameStarter = new GameStarter(
                    tempGeneralInfo.get(ICON_TAG),
                    tempGeneralInfo.get(NAME_TAG),
                    tempFiles,
                    (ex) -> this.myExceptionDisplayer.displayException(ex),
                    myLanguageBundle,
                    filesDisplayIcon,
                    filesDisplayStatus);

            myFlowPane.getChildren().add(tempGameStarter.getView());
            myGameStarters.add(tempGameStarter);
        }

        myVBox.getChildren().add(myFlowPane);
    }

    private void updateCSS(String newStyleSheet) {
        myVBox.getStylesheets().clear();
        myVBox.getStylesheets().add(PATH_TO_STYLESHEETS.concat(newStyleSheet));
    }

    public void updateLanguage(String newLanguage) {
        myLanguageBundle.setLanguage(newLanguage);
        for (GameStarter tempGameStarter :myGameStarters) tempGameStarter.updateLanguage();
        myExceptionDisplayer.updateLanguage();
    }

    @Override
    public VBox getView() {
        return myVBox;
    }
 }
