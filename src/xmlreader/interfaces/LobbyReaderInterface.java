package xmlreader.interfaces;

import java.util.List;
import java.util.Map;

public interface LobbyReaderInterface {

    /**
     * Get meta parameters (for now just link to stylesheet)
     * @return
     */
    String getStylesheet();

    /**
     * Get a list of maps for all icon arguments
     * @return
     */
    List<Map<String, String>> getBundleArguments();

}
