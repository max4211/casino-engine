package GameView;

import GameView.Players.BetView;
import GameView.Players.PlayerDetailView;
import GameView.Players.PlayerView;
import Utility.CardTriplet;
import data.xmlreader.Pair;
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

        PlayerView PV = new PlayerView("Eric", 100., 1);
        root.setCenter(PV.getView());

        CardTriplet a = new CardTriplet(1., "hearts", 1);
        CardTriplet b = new CardTriplet(2., "spades", 2);

        List<CardTriplet> ab = new ArrayList<>();
        ab.add(a);
        ab.add(b);

        PV.addBet(ab, 10., 50);
        PV.addBet(ab, 20., 20);

        PV.addCard(a, 50);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
