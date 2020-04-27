package ooga;

import UI.LobbyView.LobbyView;
import UI.Utilities.ScreenPosition;
import exceptions.GeneralXMLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main extends Application {

    private static final String LOBBY_XML_FILE = "data/xml/lobbyview/lobbyview_bad.xml";
    private static final int EXIT_STATUS = 0;
    private static final int TWO = 2;
    public static void main(String[] args) {
        launch(args);
    }

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
