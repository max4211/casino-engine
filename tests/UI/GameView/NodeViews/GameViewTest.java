package UI.GameView.NodeViews;

import UI.GameView.GameView;
import UI.LobbyView.DukeApplicationTest;
import Utility.CardTriplet;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameViewTest extends DukeApplicationTest {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 1000;
    private GameView myGameView;

    @BeforeEach
    public void setUp() throws Exception {
        launch(Main.class);
    }

    @Test
    void testStandardBlackJack() {
        myGameView.addPlayer("Eric", 1, 1000);
        myGameView.addPlayer("Max", 2, 2000);
        myGameView.addPlayer("Duvall", 3, 3000);

        CardTriplet c1 = new CardTriplet(11, "hearts", 1);
        CardTriplet c2 = new CardTriplet(5, "spades", 2);
        List<CardTriplet> handList = new ArrayList<>();
        handList.add(c1);
        handList.add(c2);
        myGameView.addBet(handList, 20, 1, 4);
    }
}