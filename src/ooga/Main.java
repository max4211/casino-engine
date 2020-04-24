package ooga;

import UI.LobbyView.LobbyView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import xml.xmlreader.readers.LobbyReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    private static final String LOBBY_XML_FILE = "data/xml/lobbyview/lobbyview_v1.xml";
    private static final int EXIT_STATUS = 0;
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
            primaryStage.show();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.exit(EXIT_STATUS);
        }
    }
}
