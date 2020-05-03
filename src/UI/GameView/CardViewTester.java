package UI.GameView;

import UI.Utilities.CardTriplet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Basic class used to test the CardView. This is NOT relevant to any functionality or design in the project. This was only used as a test class prior to using JUnit TestFX.
 */
public class CardViewTester extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CardView tempCV = new CardView(new CardTriplet(10, "heart", 0), "fancyCardDown.png");

        Scene newScene = new Scene(tempCV.getView());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }
}
