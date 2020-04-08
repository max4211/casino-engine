package GameView;

import Utility.CardTriplet;
import engine.dealer.Card;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
