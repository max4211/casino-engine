package ooga;

import UI.LobbyView.LobbyView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final boolean LOBBY_START = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        if (LOBBY_START) {
            LobbyView myLobby = new LobbyView();
            Scene myScene = new Scene(myLobby.getView(), 500, 400);

            primaryStage.setScene(myScene);
            primaryStage.show();
        } else {
            final String deckFile = "src/data/deck/standard.xml";
            final String gameFile = "src/data/game/blackjackGame_v2.xml";
            final String playerFile = "src/data/players/players.xml";
            final String handFile = "src/data/hands/hands.xml";
            final String viewFile = "src/data/view/view.xml";
            GameConstructor gtor = new GameConstructor(deckFile, gameFile, playerFile, handFile, viewFile);
        }
    }
}
