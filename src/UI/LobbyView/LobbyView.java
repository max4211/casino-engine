package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.layout.FlowPane;
import ooga.GameConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LobbyView implements NodeViewInterface {

    FlowPane myFlowPane;

    public LobbyView() {
        myFlowPane = new FlowPane();

        List<String> ALL_XMLS = new ArrayList<>();
        ALL_XMLS = new ArrayList<>();
        ALL_XMLS.add("deck");
        ALL_XMLS.add("game");
        ALL_XMLS.add("players");
        ALL_XMLS.add("hands");
        ALL_XMLS.add("view");

        CustomGameIcon myButton = new CustomGameIcon("BlackJackIcon.png", c -> createGame(c), ALL_XMLS);
        myFlowPane.getChildren().add(myButton.getView());
    }

    @Override
    public FlowPane getView() {
        return myFlowPane;
    }

    private void createGame(Map<String, File> myXMLFiles) {
        GameConstructor newGame = new GameConstructor(myXMLFiles);
    }
 }
