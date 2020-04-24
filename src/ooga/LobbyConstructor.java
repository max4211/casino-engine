package ooga;

import UI.LobbyView.LobbyView;
import UI.LobbyView.XMLChooser;
import exceptions.ValidatorException;
import org.xml.sax.SAXException;
import xml.xmlreader.readers.LobbyReader;
import xml.xmlvalidator.LobbyViewValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LobbyConstructor {

    private static final String LOBBY_PATH = "lobbyview";

    private File myLobbyFile;
    private LobbyViewValidator myValidator;

    private int myWidth = 300;
    private int myHeight = 500;

    public LobbyConstructor(String lobbyFile) {
        this.myLobbyFile = new File(lobbyFile);
        this.myValidator = new LobbyViewValidator();
    }

    public LobbyView getLobbyView() throws IOException, SAXException, ParserConfigurationException {
        while (!(lobbyFileIsValid())) {
            this.myLobbyFile = XMLChooser.getGeneralFile(LOBBY_PATH);
        }
        return constructLobbyView();
    }

    public int getWidth() {
        return this.myWidth;
    }

    public int getHeight() {
        return this.myHeight;
    }

    private LobbyView constructLobbyView() throws ParserConfigurationException, SAXException, IOException {
        LobbyReader myLobbyReader = new LobbyReader(this.myLobbyFile);
        List<String> myLobbyCSS = myLobbyReader.getLobbyStylesheet();
        List<String> myLobbyLanguages = myLobbyReader.getLobbyLanguages();
        String myErrorCSS = myLobbyReader.getErrorStylesheet();
        String myErrorIcon = myLobbyReader.getErrorIcon();
        List<Map<String, String>> myLobbyInfo = myLobbyReader.getBundleArguments();
        List<List<File>> myLobbyFiles = myLobbyReader.getFileTags();
        this.myWidth = myLobbyReader.getScreenWidth();
        this.myHeight = myLobbyReader.getScreenHeight();
        return new LobbyView(myLobbyCSS, myLobbyLanguages, myErrorIcon, myErrorCSS, myLobbyInfo, myLobbyFiles);
    }

    private boolean lobbyFileIsValid() {
        try {
            return this.myValidator.validate(this.myLobbyFile);
        } catch (ValidatorException e) {
            return false;
        }
    }

}
