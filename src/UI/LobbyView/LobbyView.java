package UI.LobbyView;

import UI.ExceptionDisplay.ExceptionDisplayer;
import UI.Interfaces.StylizedNode;
import UI.Settings.SettingsBar;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import Utility.lobbyviewbundle.LobbyViewBundle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Class that represents the Lobby of a game, displaying multiple GameStarters in by means of a FlowPane held in a VBox.
 * Has a SettingsBar on top of the display (at the first position in the VBox) that allows stylization of CSS and language changes.
 * Implements StylizedNode, returning the VBox with a CSS ID of LobbyView.
 * @author Eric Doppelt and Max Smith. Note that Max Smith only handled the LobbyView Bundle implementation.
 */
public class LobbyView implements StylizedNode {

    VBox myVBox;
    FlowPane myFlowPane;

    private static final String WEBSITE_URL = "http://casino308.com/pages/lobby.html";

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

    /**
     * Basic constructor that creates the entire LobbyView, which is then accessible via getView().
     * @param bundle is a LobbyViewBundle, which encapsulates information regarding the iconBundles to display in the UI, the files to associate with each game starter,
     *               the CSS options and language options to display, and the CSS style of the exceptions thrown in LobbyView.
     */
    public LobbyView(LobbyViewBundle bundle) {
        initVBOX();
        ResourceBundle myIconResources = initCSS(bundle.getStylesheets(), bundle.getIconProperties());
        initLanguageBundle(bundle.getLanguages());
        initExceptionDisplayer(myIconResources, bundle.getErrorCSS());
        addSettingsBar(bundle.getStylesheets(), bundle.getLanguages(), myIconResources);
        initFlowPane();
        createIcons(bundle.getGeneralinfo(), bundle.getFiles(),
                bundle.getFilesDisplayIcon(), bundle.getFilesDisplayStatus());
        myVBox.getChildren().add(myFlowPane);
    }

    /**
     * Method that changes the underlying ResourceBundle of the LanguageBundle and then tells all classes composed with it to update their displays.
     * @param newLanguage is the new language, given the a LanguagePicker.
     */
    public void updateLanguage(String newLanguage) {
        myLanguageBundle.setLanguage(newLanguage);
        for (GameStarter tempGameStarter :myGameStarters) tempGameStarter.updateLanguage();
        myExceptionDisplayer.updateLanguage();
    }

    /**
     * Method that simply returns a Node encompassing the entire LobbyView.
     * @return a VBox containing a SettingsBar and a FlowPane of all GameStarters.
     */
    @Override
    public VBox getView() {
        return myVBox;
    }

    private void createIcons(List<Map<String, String>> generalinfo, List<List<File>> files, String filesDisplayIcon, String filesDisplayStatus) {
        for (int i = 0; i < generalinfo.size(); i++) {
            Map<String, String> tempGeneralInfo = generalinfo.get(i);
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
    }

    private void initFlowPane() {
        myFlowPane = new FlowPane();
        Formatter.formatGameStarterFlowPane(myFlowPane);
    }

    private void addSettingsBar(List<String> stylesheets, List<String> languages, ResourceBundle myIconResources) {
        SettingsBar addedSettings = new SettingsBar(
                e -> updateCSS(e), stylesheets, e -> updateLanguage(e),
                languages, PATH_TO_ICON_IMAGE.concat(myIconResources.getString(INFO_TAG)),
                WEBSITE_URL);
        myVBox.getChildren().add(addedSettings.getView());
    }

    private void initExceptionDisplayer(ResourceBundle myIconResources, String errorCSS) {
        myExceptionDisplayer = new ExceptionDisplayer(myIconResources.getString(ERROR_TAG), errorCSS, myLanguageBundle);
    }

    private void initLanguageBundle(List<String> languages) {
        myLanguageBundle = new LanguageBundle(languages.get(DEFAULT_LANGUAGE_INDEX));
    }

    private void initVBOX() {
        myVBox = new VBox();
        StylizedNode.setStyleID(myVBox, this.getClass());
        Formatter.formatLobbyView(myVBox);
    }

    private ResourceBundle initCSS(List<String> styleSheets, String iconProperties) {
        myGameStarters = new ArrayList<>();
        updateCSS(styleSheets.get(DEFAULT_CSS_INDEX));
        return ResourceBundle.getBundle(PATH_TO_ICON_BUNDLE.concat(iconProperties));
    }

    private void updateCSS(String newStyleSheet) {
        myVBox.getStylesheets().clear();
        myVBox.getStylesheets().add(PATH_TO_STYLESHEETS.concat(newStyleSheet));
    }
 }
