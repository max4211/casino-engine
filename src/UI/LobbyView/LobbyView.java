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

    private static final String DECK_TAG = "Deck";
    private static final String GAME_TAG = "Game";
    private static final String HAND_TAG = "Hands";
    private static final String PLAYER_TAG = "Players";
    private static final String VIEW_TAG = "View";

    private static final String CUSTOM = "Custom";
    private static final String STANDARD = "Standard";

    public LobbyView(String styleSheet, List<Map<String, String>> generalInfo, List<Map<String, File>> files) {
        myFlowPane = new FlowPane();
        myFlowPane.getStylesheets().add(styleSheet);

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
