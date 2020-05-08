package UI.LobbyView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Basic FileChooser class that only accepts XML files and starts at an initial directory of the game's XML data.
 * This only accepts one file and is used in the Main class to select a proper LobbyView after the input of bad data. Game XML files are handled via a MultipleXMLChooser.
 * @author Eric Doppelt
 */
public class XMLChooser {

    private static final String ACCEPTED_XML_DESCRIPTION = "All XMLs";
    private static final String ACCEPTED_XML_EXTENSION = "*.xml";

    private static final String USER_DIRECTORY = "user.dir";
    private static final String PATH_TO_XML = "/data/xml/";
    private static final String DIRECTORY_TO_XML = System.getProperty(USER_DIRECTORY).concat(PATH_TO_XML);

    /**
     * Basic class that creates a file chooser display and returns the result of the file selected.
     * Opens to a specific directory specified by the path parameter.
     * @param path is the path to a subdirectory that is concatenated onto a path to the XML data.
     * @return the File that is selected by the user.
     */
    public static File getGeneralFile(String path) {
        FileChooser result = new FileChooser();
        result.setInitialDirectory(new File(DIRECTORY_TO_XML + path));
        result.getExtensionFilters().add(new FileChooser.ExtensionFilter(ACCEPTED_XML_DESCRIPTION, ACCEPTED_XML_EXTENSION));
        return result.showOpenDialog(new Stage());
    }
}
