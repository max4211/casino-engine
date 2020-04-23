package UI.LobbyView;

import UI.ExceptionHandling.ExceptionDisplayer;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import exceptions.NullFileException;
import javafx.scene.layout.FlowPane;
import ooga.GameConstructor;

import java.io.File;
import java.util.List;
import java.util.Map;

public class LobbyView implements NodeViewInterface {

    FlowPane myFlowPane;

    private static final String NAME_TAG = "Name";
    private static final String TYPE_TAG = "Type";
    private static final String ICON_TAG = "Icon";

    private static final String CUSTOM = "Custom";
    private static final String STANDARD = "Standard";

    private static final String FLOWPANE_CSS_ID = "full-lobby";
    private static final int DEFAULT_CSS_INDEX = 0;

    private LanguageBundle myLanguageBundle;
    private ExceptionDisplayer myExceptionDisplayer;

    public LobbyView(List<String> styleSheets, List<String> languages, String errorIcon, String errorCSS, List<Map<String, String>> generalInfo, List<Map<String, File>> files) {
        myFlowPane = new FlowPane();
        myFlowPane.setId(FLOWPANE_CSS_ID);
        myFlowPane.getStylesheets().add(styleSheets.get(DEFAULT_CSS_INDEX));

        //FIXME: this is bad
        myLanguageBundle = new LanguageBundle("English");
        myExceptionDisplayer = new ExceptionDisplayer(errorIcon, errorCSS, myLanguageBundle);

        for (int i = 0; i < generalInfo.size(); i++) {
            Map<String, String> tempGeneralInfo = generalInfo.get(i);
            Map<String, File> tempFiles = files.get(i);

            String gameType = generalInfo.get(i).get(TYPE_TAG);
            GameIcon tempIcon = null;
            // TODO - change this to reflection
            // Reflect and make icon, send off to validator
            if (gameType.equals(CUSTOM)) {
                tempIcon = new CustomGameIcon(tempGeneralInfo.get(ICON_TAG), tempGeneralInfo.get(NAME_TAG), e -> createGame(e), tempFiles.keySet());
            } else if (gameType.equals(STANDARD)) {
                tempIcon = new StandardGameIcon(tempGeneralInfo.get(ICON_TAG), tempGeneralInfo.get(NAME_TAG), e -> createGame(e), tempFiles);
            }
            myFlowPane.getChildren().add(tempIcon.getView());
        }
    }

    @Override
    public FlowPane getView() {
        return myFlowPane;
    }

    private void createGame(Map<String, File> myXMLFiles) {
        // TODO - called validator with all files
        // Validate file schema, general files (hand, deck, players...)
        // Tell Eric - which file is wrong, why its wrong
        for (String xmlType : myXMLFiles.keySet()) {
            if (myXMLFiles.get(xmlType) == null) {
                myExceptionDisplayer.displayException(new NullFileException(xmlType));
                File newXml = XMLChooser.getXMLFile(xmlType);
                if (newXml == null) return;
                myXMLFiles.put(xmlType, newXml);
            }
        }
        GameConstructor newGame = new GameConstructor(myXMLFiles);
    }
 }
