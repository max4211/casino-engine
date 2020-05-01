package ooga;

import UI.LobbyView.LobbyView;
import UI.Utilities.ScreenPosition;
import exceptions.GeneralXMLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main method to run the program
 * First attempts to construct a lobbyview from a constructor, then starts the game
 * @author Max Smith and Eric Doppelt
 */
public class Main extends Application {

    private static final String LOBBY_XML_FILE = "data/xml/lobbyview/lobbyview_bad.xml";
    private static final int EXIT_STATUS = 0;
    private static final int TWO = 2;

    /**
     * Launch program from string[] arguments
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the game using the primary stage
     * @param primaryStage is the first stage to be displayed
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            LobbyConstructor myLobbyConstructor = new LobbyConstructor(LOBBY_XML_FILE);
            LobbyView myLobbyView = myLobbyConstructor.getLobbyView();
            int width = myLobbyConstructor.getWidth();
            int height = myLobbyConstructor.getHeight();
            Scene myScene = new Scene(myLobbyView.getView(), width, height);
            primaryStage.setScene(myScene);
            primaryStage.setX(ScreenPosition.MIDDLE.getX() - width / TWO);
            primaryStage.setY(ScreenPosition.MIDDLE.getY());
            primaryStage.show();
        } catch (GeneralXMLException e) {
            System.exit(EXIT_STATUS);
        }
    }
}
