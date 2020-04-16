package xmlreader.interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface LobbyReaderInterface {

    /**
     * Get meta parameters (for now just link to stylesheet)
     * @return
     */
    String getLobbyStylesheet();
    String getErrorStylesheet();
    String getErrorIcon();

    /**
     * Get a list of maps for all icon arguments
     * @return
     */
    List<Map<String, String>> getBundleArguments();

    /**
     * Get a list of maps for all file arguments
     * @return
     */
    List<Map<String, File>> getFileTags();

}
