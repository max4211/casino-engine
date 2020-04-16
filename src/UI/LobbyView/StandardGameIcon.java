package UI.LobbyView;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public class StandardGameIcon extends GameIcon {

    private Map<String, File> myFiles;

    public StandardGameIcon(String imageFile, String gameName, Consumer<Map<String, File>> myFileAccepter, Map<String, File> xmlFiles) {
        super(imageFile, gameName, myFileAccepter);
        myFiles = xmlFiles;
    }

    @Override
    protected Map<String, File> getFiles() {
        return myFiles;
    }
}
