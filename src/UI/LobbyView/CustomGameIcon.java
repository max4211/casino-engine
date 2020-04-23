package UI.LobbyView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class CustomGameIcon extends GameIcon {

    private Set<String> myNeededXMLs;

    public CustomGameIcon(String imageFile, String gameName, Consumer<Map<String, File>> myFileAccepter, Set<String> neededXMLs) {
        super(imageFile, gameName, myFileAccepter);
        myNeededXMLs = neededXMLs;
    }

    // TODO - get list of files from
    // Immediately call file validator with a bundle of files
    @Override
    protected Map<String, File> getFiles() {
        Map<String, File> returnedMap = new HashMap<>();
        for (String xml : myNeededXMLs) {
            File chosenXML = XMLChooser.getXMLFile(xml);
            returnedMap.put(xml, chosenXML);
        }
        return returnedMap;
    }
}
