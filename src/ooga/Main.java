package ooga;

import UI.LobbyView.LobbyView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import xmlreader.readers.LobbyReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    private static final String PATH_TO_LOBBY_XML = "data/xml/lobbyview/";
    private static final String LOBBY_XML_FILE = "BlackJackLobby.xml";
    private static final int EXIT_STATUS = 0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LobbyView myLobbyView;
        try {
            LobbyReader myLobbyReader = new LobbyReader(PATH_TO_LOBBY_XML + LOBBY_XML_FILE);
            String myLobbyCSS = myLobbyReader.getLobbyStylesheet();
            String myErrorCSS = myLobbyReader.getErrorStylesheet();tr
            List<Map<String, String>> myLobbyInfo = myLobbyReader.getBundleArguments();
            List<Map<String, File>> myLobbyFiles = myLobbyReader.getFileTags();
            myLobbyView = new LobbyView(myLobbyCSS, myLobbyInfo, myLobbyFiles);
            Scene myScene = new Scene(myLobbyView.getView(), 500, 400);
            primaryStage.setScene(myScene);
            primaryStage.show();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.exit(EXIT_STATUS);
        }
    }
}
