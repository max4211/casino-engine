package GameView;

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
        Pair<Double, String> tempCard = new Pair(11., "heart");
        CardView tempCV = new CardView(tempCard);

        Pair<Double, String> tempCard1 = new Pair(12., "spades");
        CardView tempCV1 = new CardView(tempCard);

        List tempList = new ArrayList();
        tempList.add(tempCard1);
        tempList.add(tempCard);

        double wagerAmount = 5.;

        BetView tempBetView = new BetView(tempList, wagerAmount);
        root.setCenter(tempBetView.getView());
        tempBetView.addCard(new Pair<Double, String>(11., "string"));
        tempBetView.showCard(1);

        List<String> allActions = new ArrayList<>();
        allActions.add("MAX");
        allActions.add("SMITH");
        allActions.add("HELLO");

        TableView tableView = new TableView("StandardBJTable.jpeg");
        root.setBottom(tableView.getView());

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
