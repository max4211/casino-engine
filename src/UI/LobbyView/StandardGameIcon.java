package UI.LobbyView;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

public class StandardGameIcon extends GameIcon {

    private Map<String, File> myFiles;

    public StandardGameIcon(String imageFile, Consumer<Map<String, File>> myFileAccepter, Map<String, File> xmlFiles) {
        super(imageFile, myFileAccepter);
        myFiles = xmlFiles;
    }

    @Override
    protected Map<String, File> getFiles() {
        return myFiles;
    }
}
