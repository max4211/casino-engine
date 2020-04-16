package UI.LobbyView;

import UI.ExceptionHandling.ExceptionHandler;
import UI.Interfaces.NodeViewInterface;
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

    public LobbyView(String styleSheet, List<Map<String, String>> generalInfo, List<Map<String, File>> files) {
        myFlowPane = new FlowPane();
        myFlowPane.setId(FLOWPANE_CSS_ID);
        myFlowPane.getStylesheets().add(styleSheet);

        for (int i = 0; i < generalInfo.size(); i++) {
            Map<String, String> tempGeneralInfo = generalInfo.get(i);
            Map<String, File> tempFiles = files.get(i);

            String gameType = generalInfo.get(i).get(TYPE_TAG);
            GameIcon tempIcon = null;
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
        for (String xmlType : myXMLFiles.keySet()) {
            if (myXMLFiles.get(xmlType) == null) {
                ExceptionHandler.displayException(new NullFileException(xmlType));
                myXMLFiles.put(xmlType, XMLChooser.getXMLFile(xmlType));
            }
        }
        GameConstructor newGame = new GameConstructor(myXMLFiles);
    }
 }
