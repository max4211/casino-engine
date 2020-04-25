package Utility.lobbyviewbundle;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface LobbyViewBundleInterface {

    /**
     * Getters for all data in the lobby view bundle
     * @return
     */
     List<String> getStylesheets();
     List<String> getLanguages();
     String getIconProperties();
     String getErrorCSS();
     List<Map<String, String>> getGeneralinfo();
     List<List<File>> getFiles();
     String getFilesDisplayIcon();
     String getFilesDisplayStatus();
}
