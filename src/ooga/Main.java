package ooga;

import GameView.NodeViews.GameView;
import controller.Controller;
import data.xmlreader.GameReader;
import data.xmlreader.HandReader;
import data.xmlreader.Pair;
import data.xmlreader.PlayerReader;
import engine.dealer.Dealer;
import engine.dealer.Deck;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.evaluator.HandEvaluator;
import engine.player.Player;
import engine.player.PlayerList;
import engine.table.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Main extends Application {

    private static final String gameFile = "src/data/game/blackjackGame_v2.xml";
    private static final String playerFile = "src/data/players/players.xml";
    private static final String handFile = "src/data/hands/hands.xml";

    private static Collection<Player> createPlayerList(PlayerReader playerReader) {
        Collection<Utility.Pair> playerCollection = playerReader.getPlayers();
        PlayerList myPlayers = new PlayerList(playerCollection);
        return myPlayers.getPlayers();
    }

    private static Table constructTable(GameReader gameReader, PlayerReader playerReader) {
        Collection<Player> playerList = createPlayerList(playerReader);
        List<Pair> deckList = gameReader.getDeck();
        double tableMin = gameReader.getTableMin();
        double tableMax = gameReader.getTableMax();
        Deck myDeck = new Deck(deckList);
        Dealer myDealer = new Dealer(myDeck);
        Table myTable = new Table(playerList, myDealer, tableMin, tableMax);

        return myTable;
    }

    private static Controller constructController(GameReader gameReader, HandReader handReader, Table myTable, GameView myGameView) {
        String myEntryBet = gameReader.getEntryBet();
        Collection<String> myPlayerActions = gameReader.getPlayerAction();
        Pair myDealerAction = gameReader.getDealerAction();
        Collection<String> myWinningHands = handReader.getWinningHands();
        Collection<String> myLosingHands = handReader.getLosingHands();
        HandClassifier myHandClassifier = new HandClassifier(myWinningHands, myLosingHands);
        HandEvaluator myHandEvaluator = new HandEvaluator();
        BetEvaluator myBetEvaluator = new BetEvaluator(myHandEvaluator);
        String myCompetition = gameReader.getCompetition();
        String myCardShow = gameReader.getCardShow();

        return new Controller(myTable, myGameView, myEntryBet, myPlayerActions, myDealerAction, myHandClassifier, myBetEvaluator, myCompetition, myCardShow);
    }

    // TODO - give game view parameters form the XML file
    private static GameView constructGameView() {
        GameView gameView = new GameView();
        gameView.renderTable("StandardBJTable.jpeg");
        return gameView;
    }

    // TODO - refactor game code into non static objects, currently running through main must be static
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws ParserConfigurationException, SAXException, IOException {
        GameReader gameReader = new GameReader(gameFile);
        HandReader handReader = new HandReader(handFile);
        PlayerReader playerReader = new PlayerReader(playerFile);

        Table myTable = constructTable(gameReader, playerReader);
        GameView myGameView = constructGameView();
        primaryStage.setScene(new Scene(myGameView.getView(), 800, 800));
        primaryStage.show();

        Controller myController = constructController(gameReader, handReader, myTable, myGameView);
        myController.startGame();
    }
}
