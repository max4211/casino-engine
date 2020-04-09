package GameView;

import Utility.CardTriplet;
import data.xmlreader.Pair;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.dealer.Deck;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.evaluator.HandEvaluator;
import engine.player.Player;
import engine.table.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class basicUITester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("UI Tester");
        BorderPane root = new BorderPane();
        CardTriplet tempCard = new CardTriplet(11., "heart",1);

        CardTriplet tempCard1 = new CardTriplet(12., "spades", 2);

        List tempList = new ArrayList();
        tempList.add(tempCard);
        tempList.add(tempCard1);

        double wagerAmount = 5.;

        BetView tempBetView = new BetView(tempList, wagerAmount, 1);
        root.setCenter(tempBetView.getView());
        tempBetView.addCard(new CardTriplet(2., "clubs", 3));
        tempBetView.showCard(1);

        List<String> allActions = new ArrayList<>();
        allActions.add("MAX");
        allActions.add("SMITH");
        allActions.add("HELLO");

        List<Pair> deckConstructor = new ArrayList<>();
        deckConstructor.add(new Pair("string", "10"));
        Map<String, Double> playerMap = new HashMap<>();
        playerMap.put("Eric", 10.);

        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Eric", 10.));

        List<Pair> deckList = null;
        Deck myDeck = new Deck(deckConstructor);
        Dealer myDealer = new Dealer(myDeck);

        List<String> myWinningHands = null;
        List<String> myLosingHands = null;
        HandClassifier myHandClassifier = new HandClassifier(myWinningHands, myLosingHands);
        HandEvaluator myHandEvaluator = new HandEvaluator();
        BetEvaluator myBetEvaluator = new BetEvaluator(myHandEvaluator);
        Table myTable = new Table(playerList, myDealer, myBetEvaluator, myHandClassifier);

        ActionBoxView abv = new ActionBoxView(allActions, (e -> myTable.acceptString(e)), root);
        root.setBottom(abv.getView());
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
