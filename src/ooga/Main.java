package ooga;

import GameView.Lobby;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {

    public static void main (String[] args) {
        Platform.startup(() -> {
            try {
                Lobby myLobby = new Lobby();
                myLobby.start(new Stage());
                GameConstructor myGameConstructor = new GameConstructor(myLobby);
            } catch (Exception e) {

            }
        });
    }
}
