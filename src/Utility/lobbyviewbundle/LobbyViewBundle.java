package Utility.lobbyviewbundle;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Bundle all lobbyview files together to simplify LobbyView construction
 */
public class LobbyViewBundle implements LobbyViewBundleInterface {

    private final List<String> myStyleSheets;
    private final List<String> myLanguages;
    private final String myIconProperties;
    private final String myErrorCSS;
    private final List<Map<String, String>> myGeneralInfo;
    private final List<List<File>> myFiles;
    private final String myFilesDisplayIcon;
    private final String myFilesDisplayStatus;

    public LobbyViewBundle(List<String> styleSheets, List<String> languages, String iconProperties, String errorCSS,
                     List<Map<String, String>> generalInfo, List<List<File>> files,
                     String filesDisplayIcon, String filesDisplayStatus) {
        this.myStyleSheets = styleSheets;
        this.myLanguages = languages;
        this.myIconProperties = iconProperties;
        this.myErrorCSS = errorCSS;
        this.myGeneralInfo = generalInfo;
        this.myFiles = files;
        this.myFilesDisplayIcon = filesDisplayIcon;
        this.myFilesDisplayStatus = filesDisplayStatus;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return stylesheets in the lobbyview
     */
    @Override
    public List<String> getStylesheets() {
        return this.myStyleSheets;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return languages in the lobbyview
     */
    @Override
    public List<String> getLanguages() {
        return this.myLanguages;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return iconPropertes in the lobbyview
     */
    @Override
    public String getIconProperties() {
        return this.myIconProperties;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return errorCSS in the lobbyview
     */
    @Override
    public String getErrorCSS() {
        return this.myErrorCSS;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return generalInfo in the lobbyview
     */
    @Override
    public List<Map<String, String>> getGeneralinfo() {
        return this.myGeneralInfo;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return files in the lobbyview
     */
    @Override
    public List<List<File>> getFiles() {
        return this.myFiles;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return filesDisplayIcon in the lobbyview
     */
    @Override
    public String getFilesDisplayIcon() {
        return this.myFilesDisplayIcon;
    }

    /**
     * Getters for all data in the lobby view bundle
     * @return filesDisplayStatus in the lobbyview
     */
    @Override
    public String getFilesDisplayStatus() {
        return this.myFilesDisplayStatus;
    }
}
