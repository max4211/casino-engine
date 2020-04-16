package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
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

    private static final String PATH_TO_CSS = "styleSheets/";

    public LobbyView(String styleSheet, List<Map<String, String>> generalInfo, List<Map<String, File>> files) {
        myFlowPane = new FlowPane();
        System.out.println(styleSheet);
        myFlowPane.getStylesheets().add(PATH_TO_CSS + styleSheet);

        for (int i = 0; i < generalInfo.size(); i++) {
            Map<String, String> tempGeneralInfo = generalInfo.get(i);
            Map<String, File> tempFiles = files.get(i);

            String gameType = generalInfo.get(i).get(TYPE_TAG);
            GameIcon tempIcon = null;
            if (gameType.equals(CUSTOM)) {
                tempIcon = new CustomGameIcon(tempGeneralInfo.get(ICON_TAG), e -> createGame(e), tempFiles.keySet());
            } else if (gameType.equals(STANDARD)) {
                tempIcon = new StandardGameIcon(tempGeneralInfo.get(ICON_TAG), e -> createGame(e), tempFiles);
            }
            myFlowPane.getChildren().add(tempIcon.getView());
        }
    }

    @Override
    public FlowPane getView() {
        return myFlowPane;
    }

    private void createGame(Map<String, File> myXMLFiles) {
        GameConstructor newGame = new GameConstructor(myXMLFiles);
    }
 }
