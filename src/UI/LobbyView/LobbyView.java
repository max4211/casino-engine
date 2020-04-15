package UI.LobbyView;

import UI.Interfaces.NodeViewInterface;
import javafx.scene.layout.FlowPane;
import ooga.GameConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

        Map<String, File> BLACKJACK_XMLS = new HashMap<>();
        BLACKJACK_XMLS.put("deck", new File("src/data/deck/standard.xml"));
        BLACKJACK_XMLS.put("game", new File("src/data/game/blackjackGame_v2.xml"));
        BLACKJACK_XMLS.put("players", new File("src/data/players/players.xml"));
        BLACKJACK_XMLS.put("hands", new File("src/data/hands/hands.xml"));
        BLACKJACK_XMLS.put("view", new File("src/data/view/view.xml"));

        CustomGameIcon myButton = new CustomGameIcon("QuestionMark.jpg", c -> createGame(c), ALL_XMLS);
        StandardGameIcon myButton2 = new StandardGameIcon("BlackJackIcon.png", c -> createGame(c), BLACKJACK_XMLS);
        myFlowPane.getChildren().add(myButton.getView());
        myFlowPane.getChildren().add(myButton2.getView());
    }

    @Override
    public FlowPane getView() {
        return myFlowPane;
    }

    private void createGame(Map<String, File> myXMLFiles) {
        GameConstructor newGame = new GameConstructor(myXMLFiles);
    }
 }
