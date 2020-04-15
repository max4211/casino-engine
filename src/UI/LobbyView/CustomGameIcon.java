package UI.LobbyView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CustomGameIcon extends GameIcon {

    private List<String> myXMLFiles;

    public CustomGameIcon(String imageFile, Consumer<Map<String, File>> myFileAccepter, List<String> allXMLs) {
        super(imageFile, myFileAccepter);
        myXMLFiles = allXMLs;
    }

    @Override
    protected Map<String, File> getFiles() {
        Map<String, File> returnedMap = new HashMap<>();
        for (String xml : myXMLFiles) {
            File chosenXML = XMLChooser.getXMLFile(xml);
            returnedMap.put(xml, chosenXML);
        }
        return returnedMap;
    }
}
