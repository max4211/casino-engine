package Utility.lobbyviewbundle;

import java.io.File;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<String> getStylesheets() {
        return this.myStyleSheets;
    }

    @Override
    public List<String> getLanguages() {
        return this.myLanguages;
    }

    @Override
    public String getIconProperties() {
        return this.myIconProperties;
    }

    @Override
    public String getErrorCSS() {
        return this.myErrorCSS;
    }

    @Override
    public List<Map<String, String>> getGeneralinfo() {
        return this.myGeneralInfo;
    }

    @Override
    public List<List<File>> getFiles() {
        return this.myFiles;
    }

    @Override
    public String getFilesDisplayIcon() {
        return this.myFilesDisplayIcon;
    }

    @Override
    public String getFilesDisplayStatus() {
        return this.myFilesDisplayStatus;
    }
}
