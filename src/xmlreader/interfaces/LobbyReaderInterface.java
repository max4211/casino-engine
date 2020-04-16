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
    List<Map<String, String>> getIconArgumetns();

//    /**
//     * Get XML String elements for creating the mini view in lobby view
//     * @return
//     */
//    String getName();
//    String getIcon();
//
//    /**
//     * Get XML String elements for all file pointers
//     * @return
//     */
//    String getDeckFile();
//    String getGameFile();
//    String getHandFile();
//    String getPlayerFile();
//    String getViewFile();

}
