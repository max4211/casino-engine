package GameView;

import GameView.NodeViews.GameView;
import GameView.NodeViews.PlayerView;
import GameView.NodeViews.TableView;
import Utility.CardTriplet;
import javafx.application.Application;
import javafx.scene.Scene;
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
        BorderPane root = new BorderPane();

        PlayerView PV = new PlayerView("Eric", 100., 1);
        //root.setCenter(PV.getView());

        CardTriplet a = new CardTriplet(1., "hearts", 1);
        CardTriplet b = new CardTriplet(2., "spades", 2);

        List<CardTriplet> ab = new ArrayList<>();
        ab.add(a);
        ab.add(b);

        PV.addBet(ab, 10., 50);
        PV.addBet(ab, 20., 20);

        PV.addCard(a, 50);

        TableView tb = new TableView("StandardBJTable.jpeg");
        root.setCenter(tb.getView());

        GameView gv = new GameView();
        List<String> myList = new ArrayList<>();
        myList.add("max");
        myList.add("smith");

        double min = .1;
        double max = 100.5;

        System.out.println(gv.getAction(myList));
        System.out.println(gv.getWager(0.1, max));
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
