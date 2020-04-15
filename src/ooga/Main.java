package ooga;

import UI.LobbyView.LobbyView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        LobbyView myLobby = new LobbyView();
        Scene myScene = new Scene(myLobby.getView(), 500, 500);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
