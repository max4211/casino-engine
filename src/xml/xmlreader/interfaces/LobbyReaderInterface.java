package xml.xmlreader.interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Interface implemented by lobby reader
 * @author Max Smith
 */
public interface LobbyReaderInterface {

    /**
     * Get dimensions of the screen
     * @return screen width
     */
    int getScreenWidth();

    /**
     * Get dimensions of the screen
     * @return screen height
     */
    int getScreenHeight();

    /**
     * Get meta parameters for lobby
     * @return stylesheet
     */
    List<String> getLobbyStylesheet();

    /**
     * Get meta parameters for lobby
     * @return languages
     */
    List<String> getLobbyLanguages();

    /**
     * Get meta parameters for errors
     * @return error stylesheet
     */
    String getErrorStylesheet();

    /**
     * Get meta parameters for lobby
     * @return icon properties
     */
    String getIconProperties();

    /**
     * Get a list of maps for all icon arguments
     * @return
     */
    List<Map<String, String>> getBundleArguments();

    /**
     * Get a list of maps for all file arguments
     * @return maps of files
     */
    List<List<File>> getFileTags();

    /**
     * Get metadata that drives game starter all files display
     * @return status of files
     */
    String getFilesDisplayStatus();

    /**
     * Get metadata that drives game starter all files display
     * @return icons for files
     */
    String getFilesDisplayIcon();

}
