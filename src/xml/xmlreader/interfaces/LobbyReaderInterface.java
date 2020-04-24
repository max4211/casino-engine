package xml.xmlreader.interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface LobbyReaderInterface {

    /**
     * Get dimensions of the screen
     * @return
     */
    int getScreenWidth();
    int getScreenHeight();

    /**
     * Get meta parameters (for now just link to stylesheet)
     * @return
     */
    List<String> getLobbyStylesheet();
    List<String> getLobbyLanguages();

    String getErrorStylesheet();
    String getIconProperties();

    /**
     * Get a list of maps for all icon arguments
     * @return
     */
    List<Map<String, String>> getBundleArguments();

    /**
     * Get a list of maps for all file arguments
     * @return
     */
    List<List<File>> getFileTags();

}
