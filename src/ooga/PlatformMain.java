package ooga;

import GameView.Lobby;
import javafx.application.Platform;
import javafx.stage.Stage;

public class PlatformMain {

    public static void main (String[] args) {
        Platform.startup(() -> {
            try {
                Lobby myLobby = new Lobby();
                myLobby.start(new Stage());
                // TODO - create game constructor
            } catch (Exception e) {
                ;
            }
        });
    }
}
