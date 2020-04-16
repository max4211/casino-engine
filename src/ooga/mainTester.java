package ooga;

import UI.GameView.GameView;
import controller.Controller;
import Utility.StringPair;
import controller.*;
import engine.dealer.Dealer;
import engine.dealer.Deck;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.evaluator.handevaluator.HandEvaluator;
import engine.player.Player;
import engine.player.PlayerList;
import engine.table.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import xmlreader.readers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class mainTester extends Application {

    private static final String deckFile = "src/data/deck/standard.xml";
    private static final String gameFile = "src/data/game/blackjackGame_v2.xml";
    private static final String playerFile = "src/data/players/players.xml";
    private static final String handFile = "src/data/hands/hands.xml";
    private static final String viewFile = "src/data/view/view.xml";

    private static Collection<Player> createPlayerList(PlayerReader playerReader) {
        Collection<Utility.Pair> playerCollection = playerReader.getPlayers();
        PlayerList myPlayers = new PlayerList(playerCollection);
        return myPlayers.getPlayers();
    }

    private static Table constructTable(GameReader gameReader, PlayerReader playerReader, DeckReader deckReader) {
        Collection<Player> playerList = createPlayerList(playerReader);
        List<StringPair> deckList = deckReader.getDeck();
        double tableMin = gameReader.getTableMin();
        double tableMax = gameReader.getTableMax();
        Deck myDeck = new Deck(deckList);
        Dealer myDealer = new Dealer(myDeck);
        Table myTable = new Table(playerList, myDealer, tableMin, tableMax);

        return myTable;
    }

    private static Controller constructController(GameReader gameReader, HandReader handReader, Table myTable, GameView myGameView) {

        Collection<String> myPlayerActions = gameReader.getPlayerAction();
        StringPair myDealerAction = gameReader.getDealerAction();
        Collection<String> myWinningHands = handReader.getWinningHands();
        Collection<String> myLosingHands = handReader.getLosingHands();
        HandClassifier myHandClassifier = new HandClassifier(myWinningHands, myLosingHands);
        HandEvaluator myHandEvaluator = new HandEvaluator();
        BetEvaluator myBetEvaluator = new BetEvaluator(myHandEvaluator);

        // TODO - add validation to enum constants
        EntryBet myEntryBet = EntryBet.valueOf(gameReader.getEntryBet().toUpperCase());
        Competition myCompetition = Competition.valueOf(gameReader.getCompetition().toUpperCase());
        Cardshow myCardShow = Cardshow.valueOf(gameReader.getCardShow().toUpperCase());
        Goal myGoal = Goal.valueOf(gameReader.getGoal().toUpperCase());

        return new Controller(myTable, myGameView, myEntryBet, myPlayerActions, myDealerAction,
                myHandClassifier, myBetEvaluator,
                myCompetition, myCardShow, myGoal);
    }

    // TODO - give game view parameters form the XML file
    private static GameView constructGameView() {
        GameView gameView = new GameView(null);
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
        ViewReader viewReader = new ViewReader(viewFile);
        DeckReader deckReader = new DeckReader(deckFile);

        Table myTable = constructTable(gameReader, playerReader, deckReader);
        GameView myGameView = constructGameView();
        primaryStage.setScene(new Scene(myGameView.getView(), viewReader.getScreenWidth(), viewReader.getScreenWidth()));
        primaryStage.show();

        Controller myController = constructController(gameReader, handReader, myTable, myGameView);
        myController.startGame();
    }
}
