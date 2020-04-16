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
            final String deckFile = "data/xml/deck/standard.xml";
            final String gameFile = "data/xml/game/blackjackGame_v2.xml";
            final String playerFile = "data/xml/players/players.xml";
            final String handFile = "data/xml/hands/hands.xml";
            final String viewFile = "data/xml/view/view.xml";
            GameConstructor gtor = new GameConstructor(deckFile, gameFile, playerFile, handFile, viewFile);
        }
    }
}
