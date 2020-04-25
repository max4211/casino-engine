package UI.GameView.ApplicationTesters;

import UI.GameView.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameViewTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<String> css = new ArrayList<>();
        css.add("Coral");
        css.add("Light");
        List<String> language = new ArrayList<>();
        language.add("English");
        language.add    ("Spanish");
        String iconImages = "standardGame";
        String exceptionCSS = "fire";
        double width = 20;
        double height = 20;
        GameView test = new GameView(css, language, iconImages,exceptionCSS, width, height);

        Scene newScene = new Scene(test.getView());
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }
}
