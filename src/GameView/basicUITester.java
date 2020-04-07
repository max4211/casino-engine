package GameView;

import engine.dealer.Card;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class basicUITester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("UI Tester");
        BorderPane root = new BorderPane();
        Card tempCard1 = new Card("hearts", 11);
        CardView tempCardView1 = new CardView(tempCard1);
        root.setCenter(tempCardView1.getView());
        tempCardView1.showCard();

        WagerView wv = new WagerView(12);
        root.setLeft(wv.getView());


        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
