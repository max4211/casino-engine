package ooga;

import UI.GameView.CardView;
import Utility.CardTriplet;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class basicUITester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("UI Tester");

        ArrayList allchoices = new ArrayList();
        allchoices.add("Dark");
        allchoices.add("Coral");
        allchoices.add("Light");

        ArrayList allLanguages = new ArrayList();
        allLanguages.add("English");
        allLanguages.add("Spanish");

        //GameView root = new GameView(allchoices, allLanguages);


        //primaryStage.setScene(new Scene(root.getView(), 500, 500));
        //primaryStage.show();
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

        // BetView bv1 = new BetView(allCTs, 10, 20);

       //  BetView bv2 = new BetView(allCTs, -20, 20);
       //  bv2.showCard(2);
       //  bv2.showCard(-2);

       // BetView bv3 = new BetView(new ArrayList<>(), 10000, 50);

        //BetView bv4 = new BetView(new ArrayList<>(), 10000, 50);
       // bv4.addCard(ct4);

       //  p.setCenter(bv1.getView());
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
    }

    private void testSelectors(BorderPane p) {
    }
}
