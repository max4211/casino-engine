package UI.WebView;

import javafx.application.Application;
import javafx.stage.Stage;

public class WebTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Browser browser = new Browser();
        browser.render();
    }
}
