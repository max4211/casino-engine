package UI.LobbyView;

import UI.ExceptionHandling.ExceptionDisplayer;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.util.List;
import java.util.Map;

public class LobbyView implements NodeViewInterface {

    FlowPane myFlowPane;

    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";

    private static final String FLOWPANE_CSS_ID = "full-lobby";
    private static final int DEFAULT_CSS_INDEX = 0;
    private static final int DEFAULT_LANGUAGE_INDEX = 0;

    private LanguageBundle myLanguageBundle;
    private ExceptionDisplayer myExceptionDisplayer;

    public LobbyView(List<String> styleSheets, List<String> languages, String errorIcon, String errorCSS, List<Map<String, String>> generalInfo, List<List<File>> files) {
        myFlowPane = new FlowPane();
        myFlowPane.setId(FLOWPANE_CSS_ID);
        myFlowPane.getStylesheets().add(styleSheets.get(DEFAULT_CSS_INDEX));

        myLanguageBundle = new LanguageBundle(languages.get(DEFAULT_LANGUAGE_INDEX));
        myExceptionDisplayer = new ExceptionDisplayer(errorIcon, errorCSS, myLanguageBundle);

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
    }

    @Override
    public FlowPane getView() {
        return myFlowPane;
    }
 }
