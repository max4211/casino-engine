package UI.WebView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WebTester extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Browser browser = new Browser();
        Scene scene = browser.getScene(WIDTH, HEIGHT);

        primaryStage.setTitle("WebView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
