package ooga;

import UI.GameView.BetView;
import UI.GameView.CardView;
import UI.GameView.PlayerView;
import UI.Selectors.ActionSelector;
import UI.Selectors.WagerSelector;
import Utility.CardTriplet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class basicUITester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("UI Tester");
        BorderPane root = new BorderPane();

        testCards(root);
//        testBets(root);
//        testPlayers(root);
//        testSelectors(root);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private void testCards(BorderPane p) {
        CardTriplet ct1 = new CardTriplet(4, "hearts", 1);
        CardTriplet ct2 = new CardTriplet(5, "spades", 2);
        CardTriplet ct3 = new CardTriplet(6, "clubs", 3);
        CardTriplet ct4 = new CardTriplet(-10, "banana", -2);

        List<CardTriplet> allCTs = new ArrayList<>();
        allCTs.add(ct1);
        allCTs.add(ct2);
        allCTs.add(ct3);
        allCTs.add(ct4);

        CardView cv1 = new CardView(ct1);
        CardView cv2 = new CardView(ct2);
        CardView cv3 = new CardView(ct3);
        CardView cv4 = new CardView(ct4);

        p.setCenter(cv1.getView());
        // p.setCenter(cv2.getView());
        // p.setCenter(cv3.getView());
         p.setCenter(cv4.getView());


        cv2.showCard();

        cv3.showCard();
        cv3.hideCard();

        cv4.hideCard();
        cv4.showCard();
        cv4.showCard();
        cv4.hideCard();
        cv4.hideCard();
        cv4.showCard();
    }

    private void testBets(BorderPane p) {
        p.getChildren().clear();

        CardTriplet ct1 = new CardTriplet(4, "hearts", 1);
        CardTriplet ct2 = new CardTriplet(5, "spades", 2);
        CardTriplet ct3 = new CardTriplet(6, "clubs", 3);
        CardTriplet ct4 = new CardTriplet(-10, "banana", -2);

        List<CardTriplet> allCTs = new ArrayList<>();
        allCTs.add(ct1);
        allCTs.add(ct2);
        allCTs.add(ct3);
        allCTs.add(ct4);

        BetView bv1 = new BetView(allCTs, 10, 20);

        BetView bv2 = new BetView(allCTs, -20, 20);
        bv2.showCard(2);
        bv2.showCard(-2);

        BetView bv3 = new BetView(new ArrayList<>(), 10000, 50);

        BetView bv4 = new BetView(new ArrayList<>(), 10000, 50);
        bv4.addCard(ct4);

        p.setCenter(bv1.getView());
        p.setCenter(bv2.getView());
        p.setCenter(bv3.getView());
        p.setCenter(bv4.getView());
    }

    private void testPlayers(BorderPane p) {
        p.getChildren().clear();

        CardTriplet ct1 = new CardTriplet(4, "hearts", 1);
        CardTriplet ct2 = new CardTriplet(5, "spades", 2);
        CardTriplet ct3 = new CardTriplet(6, "clubs", 3);
        CardTriplet ct4 = new CardTriplet(-10, "banana", -2);

        List<CardTriplet> allCTs = new ArrayList<>();
        allCTs.add(ct1);
        allCTs.add(ct2);
        allCTs.add(ct3);
        allCTs.add(ct4);

        List<CardTriplet> allCT2 = new ArrayList<>();
        allCT2.add(ct1);
        allCT2.add(ct2);

        PlayerView pv1 = new PlayerView("Eric", 1, 1000);
        PlayerView pv2 = new PlayerView("Max", 2, -2000);
        PlayerView pv3 = new PlayerView("Duvall", -2, 40000);

        p.setCenter(pv1.getView());

        p.setCenter(pv2.getView());
        pv2.addBet(allCTs, 40, 2);
        pv2.addBet(allCT2, 60, 4);
        pv2.showCard(4, 2);

        p.setCenter(pv3.getView());
        pv3.addBet(allCTs, 40, 2);
        pv3.addBet(allCT2, 60, 4);
        pv3.clearBets();
    }

    private void testSelectors(BorderPane p) {
        p.getChildren().clear();
        List<String> noActions = new ArrayList<>();
        System.out.println(ActionSelector.selectAction(noActions));

        List<String> someActions = new ArrayList<>();
        someActions.add("Split");
        someActions.add("Hit");
        someActions.add("Fold");
        System.out.println(ActionSelector.selectAction(someActions));

        System.out.println(WagerSelector.selectWager(0, 100));
        System.out.println(WagerSelector.selectWager(50, 50));
        System.out.println(WagerSelector.selectWager(20, 10));
    }
}
