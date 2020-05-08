package ooga;

import UI.LobbyView.LobbyView;
import UI.LobbyView.XMLChooser;
import Utility.StringPair;
import Utility.lobbyviewbundle.LobbyViewBundle;
import exceptions.GeneralXMLException;
import exceptions.ValidatorException;
import org.xml.sax.SAXException;
import xml.xmlreader.readers.LobbyReader;
import xml.xmlvalidator.LobbyViewValidator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Construct a lobby object, called from main to start the game
 * @author Max Smith
 */
public class LobbyConstructor {

    private static final String LOBBY_PATH = "lobbyview";

    private File myLobbyFile;
    private final LobbyViewValidator myValidator;

    private int myWidth = 300;
    private int myHeight = 500;

    public LobbyConstructor(String lobbyFile) {
        this.myLobbyFile = new File(lobbyFile);
        this.myValidator = new LobbyViewValidator();
    }

    public LobbyView getLobbyView() throws GeneralXMLException {
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

    private LobbyView constructLobbyView() throws GeneralXMLException {
        LobbyReader myLobbyReader = new LobbyReader(this.myLobbyFile);
        List<String> myLobbyCSS = myLobbyReader.getLobbyStylesheet();
        List<String> myLobbyLanguages = myLobbyReader.getLobbyLanguages();
        String myErrorCSS = myLobbyReader.getErrorStylesheet();
        String myIconProperties = myLobbyReader.getIconProperties();
        List<Map<String, String>> myLobbyInfo = myLobbyReader.getBundleArguments();
        List<List<File>> myLobbyFiles = myLobbyReader.getFileTags();
        String filesDisplayIcon = myLobbyReader.getFilesDisplayIcon();
        String filesDisplayStatus = myLobbyReader.getFilesDisplayStatus();
        this.myWidth = myLobbyReader.getScreenWidth();
        this.myHeight = myLobbyReader.getScreenHeight();
        LobbyViewBundle myBundle = new LobbyViewBundle(
                myLobbyCSS, myLobbyLanguages, myIconProperties, myErrorCSS,
                myLobbyInfo, myLobbyFiles,
                filesDisplayIcon, filesDisplayStatus);

        return new LobbyView(myBundle);
    }

    private boolean lobbyFileIsValid() {
        try {
            return this.myValidator.validate(this.myLobbyFile);
        } catch (ValidatorException e) {
            return false;
        }
    }

}
