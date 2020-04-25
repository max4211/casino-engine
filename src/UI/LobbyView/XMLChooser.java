package UI.LobbyView;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class XMLChooser {

    private static final String ACCEPTED_XML_DESCRIPTION = "All XMLs";
    private static final String ACCEPTED_XML_EXTENSION = "*.xml";
    private static final String DIRECTORY_TO_XML = System.getProperty("user.dir") + "/data/xml/";

    private static final String USER_DIRECTORY = "user.dir";
    private static final String PATH_TO_XML = "/data/xml/";
    private static final String DIRECTORY_TO_XML = System.getProperty(USER_DIRECTORY).concat(PATH_TO_XML);

    public static File getGeneralFile(String path) {
        FileChooser result = new FileChooser();
        result.setInitialDirectory(new File(DIRECTORY_TO_XML + path));
        result.getExtensionFilters().add(new FileChooser.ExtensionFilter(ACCEPTED_XML_DESCRIPTION, ACCEPTED_XML_EXTENSION));
        return result.showOpenDialog(new Stage());
    }
}
